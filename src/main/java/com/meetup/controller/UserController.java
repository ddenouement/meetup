package com.meetup.controller;

import com.meetup.entities.User;
import com.meetup.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@Api(value = "meetup-application", description = "Operations used to manage user functionality")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/profile")
    public ResponseEntity<User> getUserProfile(@RequestBody String login) {
        return new ResponseEntity<>(userService.getProfile(login), HttpStatus.OK);
    }

    @GetMapping(value = "/availableSpeakers")
    public ResponseEntity<List<User>> getAllSpeakers() {
        return new ResponseEntity<>(userService.getAllSpeakers(), HttpStatus.OK);
    }


}
