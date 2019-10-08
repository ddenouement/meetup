package com.meetup.controller;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/speaker")
@Api(value = "meetup-application", description = "Operations used to manage speaker functionality")
public class SpeakerController {

    @Autowired
    UserService userService;


    //TODO Find out, if speaker required for meeting creation
    @PostMapping(value = "/createMeeting")
    public ResponseEntity<String> createMeeting(@RequestBody Meeting meeting, User user) {
        return userService.createMeeting(meeting, user);
    }

    @GetMapping(value = "/createMeeting/topics")
    public ResponseEntity<List<Topic>> getAvailableTopics() {
        return new ResponseEntity<>(userService.getAllTopics(), HttpStatus.OK);
    }
    
    @GetMapping(value="/test")
    public String currentUserName(Principal principal) {
        return principal.getName();

    }

}
