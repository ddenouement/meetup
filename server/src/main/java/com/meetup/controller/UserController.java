package com.meetup.controller;

import com.meetup.entities.User;
import com.meetup.service.UserService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "meetup-application", description = "Operations used to manage user functionality")
public class UserController {

    @Autowired
    private UserService userService;

    //TODO implement
    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @GetMapping(value = "/api/v1/user/profile")
    public ResponseEntity<User> getUserProfile(@RequestParam String login) {
        return new ResponseEntity<>(userService.getProfile(login),
            HttpStatus.OK);
    }

    //TODO implement
    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @GetMapping(value = "/api/v1/user/availableSpeakers")
    public ResponseEntity<List<User>> getAllSpeakers() {
        return new ResponseEntity<>(userService.getAllSpeakers(),
            HttpStatus.OK);
    }

}
