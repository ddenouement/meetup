package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.controller.jwtsecurity.JwtTokenProvider;
import com.meetup.entities.User;
import com.meetup.entities.dto.UserRegistrationDTO;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.IUserService;
import io.swagger.annotations.Api;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ... Class that handles authorization and generates token for signup
 */
@RestController
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
     * ,. SignIn   generates a token
     *
     * @param data AuthentificationRequest
     * @param response HttpServletResponse
     * @return ResponseEntity
     **/
    @PostMapping("/api/v1/user/login")
    public ResponseEntity signin(
        final @RequestBody AuthentificationRequest data,
        final HttpServletResponse response) {
        try {
            String username = data.getLogin();
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,
                    data.getPassword()));
            String token = jwtTokenProvider.createToken(username,
                findByUsernameAmongAll(username).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            System.out
                .println("Succesfull Login: " + username + "\ntoken: " + token);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/"); // global cookie accessible everywhere
            response.addCookie(cookie);
            return ok(model);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(
                "Invalid username/password", HttpStatus.UNAUTHORIZED);
        }
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
        return userService.registerAsListener(user);
    }

    /**
     * . sign as speaker
     *
     * @param user User
     * @return ResponseEntity
     */
    @PostMapping(value = "/api/v1/user/register/speaker")
    public ResponseEntity<String> registerSpeaker(
        final @RequestBody UserRegistrationDTO user) {
        return userService.registerAsSpeaker(user);
    }
}
