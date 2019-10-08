package com.meetup.controller;

import com.meetup.controller.jwtsecurity.JwtTokenProvider;
import com.meetup.entities.User;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/user")
@Api(value = "meetup-application", description = "Operations used to manage user sign in/sign up")

public class AuthorizationController {
    @Autowired
    UserService userService;
    @Autowired
    UserDaoImpl userDao;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity signin(@RequestBody AuthentificationRequest data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, findByUsernameAmongAll(username).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    public User findByUsernameAmongAll(String login) {
        User a = userDao.findUserByLogin(login);
        if (a == null) throw new BadCredentialsException("No such person");
        return a;
    }

    @PostMapping(value = "/register/listener")
    public ResponseEntity registerListener(@RequestBody User user) {
        return userService.registerAsListener(user);
    }

    @PostMapping(value = "/register/speaker")
    public ResponseEntity<String> registerSpeaker(@RequestBody User user) {
        return userService.registerAsSpeaker(user);
    }
}