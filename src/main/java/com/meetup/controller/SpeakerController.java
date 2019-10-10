package com.meetup.controller;

import com.meetup.controller.jwtsecurity.JwtTokenProvider;
import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import com.meetup.service.MeetingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/speaker")
@Api(value = "meetup-application", description = "Operations used to manage speaker functionality")
public class SpeakerController {

    MeetingService meetingService;
    JwtTokenProvider jwtTokenProvider;

    SpeakerController(@Autowired MeetingService meetingService, @Autowired JwtTokenProvider jwtTokenProvider) {
        this.meetingService = meetingService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/createMeeting")
    public ResponseEntity createMeeting(@CookieValue(value = "token", defaultValue = "") String token,
                                        @RequestBody Meeting meeting) {
        if (meetingService.createMeeting(meeting, extractLogin(token)) == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/createMeeting/topics")
    public ResponseEntity<List<Topic>> getAvailableTopics(@CookieValue(value = "token", defaultValue = "") String token) {
        if (meetingService.getAllTopics(extractLogin(token)) == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getAllMeetings")
    public ResponseEntity<List<Meeting>> getAllMeetings(@CookieValue(value = "token", defaultValue = "") String token) {
        if (meetingService.getAllMeetings(extractLogin(token)) == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/myMeetings")
    public ResponseEntity<List<Meeting>> getMyMeetings(@CookieValue(value = "token", defaultValue = "") String token) {
        if (meetingService.getSpeakerMeetings(extractLogin(token)) == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    private String extractLogin(String token) {
        return jwtTokenProvider.getUsername(token);
    }


}
