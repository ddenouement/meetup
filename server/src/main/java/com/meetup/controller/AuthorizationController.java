package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.controller.security.jwt.JwtSecurityConstants;
import com.meetup.controller.security.jwt.JwtTokenProvider;
import com.meetup.entities.User;
import com.meetup.entities.dto.RegistrationDTO;
import com.meetup.entities.dto.UserRegistrationDTO;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.IUserService;
import com.meetup.service.impl.LoginValidatorServiceImpl;
import com.meetup.service.impl.UserServiceImpl;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ... Class that handles authorization and generates token for signup
 */
@RestController
@Slf4j
@Api(value = "meetup-application")
@RequestMapping("/api/v1")
public class AuthorizationController {

    /**
     *  UserService class, methods to work with User entity.
     */
    private IUserService userService;
    /**
     * UserDao, methods to work with DB.
     */
    private UserDaoImpl userDao;
    /**
     *  Spring class.
     */
    private AuthenticationManager authenticationManager;
    /**
     *  Class to extraxt token from cookie and other useful methods.
     */
    private JwtTokenProvider jwtTokenProvider;
    /**
     * Login validation service.
     */
    private ILoginValidatorService loginValidatorService;

    @Autowired
    AuthorizationController(final UserServiceImpl userService,
        final UserDaoImpl userDao,
        final AuthenticationManager authenticationManager,
        final JwtTokenProvider jwtTokenProvider,
        final LoginValidatorServiceImpl loginValidatorService) {
        this.userService = userService;
        this.userDao = userDao;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.loginValidatorService = loginValidatorService;

    }

    /**
     *  SignIn   generates a token from succesful login and puts it in response.
     *
     * @param data AuthentificationRequest (login and password)
     * @param response HttpServletResponse
     * @return ResponseEntity
     **/
    @PostMapping("/user/login")
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
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/logout")
    public ResponseEntity logout(final HttpServletResponse response) {
        deleteToken(response);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * ,. sign as listener
     *
     * @param user User
     * @return ResponseEntity
     */
    @PostMapping(value = "/user/register/listener")
    public ResponseEntity registerListener(
        final @RequestBody UserRegistrationDTO user) {
        String userPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        String encodedPassword = encoder.encode(userPassword);
        user.setPassword(encodedPassword);
        userService.registerAsListener(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * . sign as speaker
     *
     * @param user User
     * @return ResponseEntity
     */
    @PostMapping(value = "/user/register/speaker")
    public ResponseEntity registerSpeaker(
        final @RequestBody UserRegistrationDTO user) {
        String userPassword = user.getPassword();
        String encodedPassword = new BCryptPasswordEncoder(11)
            .encode(userPassword);
        user.setPassword(encodedPassword);
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
    @PreAuthorize("hasAuthority(T(com.meetup.utils.Role).LISTENER) "
        + "AND !hasAuthority(T(com.meetup.utils.Role).SPEAKER)")
    @PutMapping(value = "/users/upgrade")
    public ResponseEntity promoteToSpeaker(
        @CookieValue("token") final String token,
        @RequestBody final UserRegistrationDTO user,
        final HttpServletResponse response) {
        Integer userId = loginValidatorService.extractId(token);
        userService.upgradeToSpeaker(user, userId);
        deleteToken(response);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Change user's password.
     *
     * @param password new password
     * @param token cookie with JWT
     * @return status
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PutMapping(value = "/user/password")
    public ResponseEntity changePassword(
        @RequestBody final String password,
        @CookieValue("token") final String token) {
        Integer userId = loginValidatorService.extractId(token);

        userService.changePassword(userId, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Send email to user.
     *
     * @param data user email and login.
     * @return status
     */
    @PostMapping(value = "/user/welcome")
    public ResponseEntity sendEmail(
        @RequestBody final RegistrationDTO data) {
        userService.sendEmail(data.getEmail(), data.getLogin());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Send email to user.
     *
     * @param data user email and login.
     * @return status
     */
    @PostMapping(value = "/user/password")
    public ResponseEntity sendNewPassword(
            @RequestBody final String data) {
        userService.sendNewPassword(data);
        return new ResponseEntity<>(HttpStatus.OK);
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

    /**
     * Helper method.
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
}
