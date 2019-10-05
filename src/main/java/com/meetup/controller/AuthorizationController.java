package com.meetup.controller;

import com.meetup.entities.Listener;
import com.meetup.entities.Speaker;
import com.meetup.service.ListenerService;
import com.meetup.service.SpeakerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Api(value = "meetup-application", description = "Operations used to manage user sign in/sign up")
public class AuthorizationController {

    @Autowired
    ListenerService listenerService;
    @Autowired
    SpeakerService speakerService;

    @PostMapping(value = "/register/listener")
    public ResponseEntity<String> registerListener(@RequestBody Listener listener) {
        return listenerService.register(listener);
    }

    @PostMapping(value = "/register/speaker")
    public ResponseEntity<String> registerSpeaker(@RequestBody Speaker speaker) {
        return speakerService.register(speaker);
    }
}