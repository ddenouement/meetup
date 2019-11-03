package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.controller.jwtsecurity.JwtSecurityConstants;
import com.meetup.controller.jwtsecurity.JwtTokenProvider;
import com.meetup.entities.User;
import com.meetup.entities.dto.UserRegistrationDTO;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.IUserService;
import com.meetup.utils.RoleProcessor;
import io.swagger.annotations.Api;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ... Class that handles authorization and generates token for signup
 */
@RestController
@Slf4j
@Api(value = "meetup-application")
public class AuthorizationController {

    /**
     * ,. UserService
     */
    @Autowired
    private IUserService userService;
    /**
     * . userDao
     */
    @Autowired
    private UserDaoImpl userDao;
    /**
     * ,. Spring class
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * ,. Class to extraxt token from cookie
     */
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    /**
     * Login validation service.
     */
    @Autowired
    private ILoginValidatorService loginValidatorService;

    /**
     * ,. SignIn   generates a token
     *
     * @param data AuthentificationRequest
     * @param response HttpServletResponse
     * @return ResponseEntity
     **/
    @PostMapping("/api/v1/user/login")
    public ResponseEntity signIn(
        final @RequestBody AuthentificationRequest data,
        final HttpServletResponse response) {
        String username = data.getLogin();
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username,
                data.getPassword()));
        User user = findByUsernameAmongAll(username);
        List<String> roles = user.getRoles().stream().map(Enum::name)
            .collect(Collectors.toList());
        String token = jwtTokenProvider
            .createToken(username, roles, user.getId());
        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        model.put("role", RoleProcessor.getRoleString(user));
        log.debug("Successful Login: " + username + "\ntoken: " + token);
        saveToken(response, token);
        return ok(model);
    }

    /**
     * Delete token from cookies.
     *
     * @param response HttpServletResponse
     * @return status of operation
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "api/v1/user/logout")
    public ResponseEntity logout(final HttpServletResponse response) {
        deleteToken(response);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * . Helper method
     *
     * @param login String
     * @return User
     */
    private User findByUsernameAmongAll(final String login) {
        User a = userDao.findUserByLogin(login);
        if (a == null) {
            throw new BadCredentialsException("No such person");
        }
        return a;
    }

    /**
     * ,. sign as listener
     *
     * @param user User
     * @return ResponseEntity
     */
    @PostMapping(value = "/api/v1/user/register/listener")
    public ResponseEntity registerListener(
        final @RequestBody UserRegistrationDTO user) {
        userService.registerAsListener(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * . sign as speaker
     *
     * @param user User
     * @return ResponseEntity
     */
    @PostMapping(value = "/api/v1/user/register/speaker")
    public ResponseEntity registerSpeaker(
        final @RequestBody UserRegistrationDTO user) {
        userService.registerAsSpeaker(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Upgrade listener to speaker and log out.
     *
     * @param token cookie with JWT
     * @param user info about upgraded user
     * @param response response to write deleted cookie with JWT to
     * @return status
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).LISTENER) "
        + "AND !hasRole(T(com.meetup.utils.Role).SPEAKER)")
    @PutMapping(value = "/api/v1/users/upgrade")
    public ResponseEntity registerSpeaker(
        @CookieValue("token") final String token,
        @RequestBody final UserRegistrationDTO user,
        final HttpServletResponse response) {
        Integer userId = loginValidatorService.extractId(token);
        userService.upgradeToSpeaker(user, userId);
        deleteToken(response);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Create a cookie and add it to response.
     *
     * @param response where to add cookie
     * @param name cookie's name
     * @param value cookie's value
     * @param maxAge cookie's max age in seconds
     */
    private void setCookie(final HttpServletResponse response,
        final String name, final String value, final int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/"); // global cookie accessible everywhere
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
     * Save a token to cookie and add it to response.
     *
     * @param response where to add cookie
     * @param token token to save
     */
    private void saveToken(final HttpServletResponse response,
        final String token) {
        setCookie(response, "token", token,
            JwtSecurityConstants.COOKIE_VALIDITY_IN_SECONDS);
    }

    /**
     * Delete a token from cookie through response.
     *
     * @param response where to overwrite cookie
     */
    private void deleteToken(final HttpServletResponse response) {
        setCookie(response, "token", null, 0);
    }
}
