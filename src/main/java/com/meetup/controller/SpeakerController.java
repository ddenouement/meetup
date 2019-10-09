package com.meetup.controller;

import com.meetup.controller.jwtsecurity.JwtTokenProvider;
import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.service.MeetingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dmytro Zubko
 */

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

    //TODO Find out, if speaker required for meeting creation
    @PostMapping(value = "/createMeeting")
    public ResponseEntity<String> createMeeting(@CookieValue(value = "token", defaultValue = "") String token,
                                                @RequestBody Meeting meeting, User user) {
        String login = jwtTokenProvider.getUsername(token);
        return meetingService.createMeeting(meeting, user);
    }

    @GetMapping(value = "/createMeeting/topics")
    public ResponseEntity<List<Topic>> getAvailableTopics() {
        return new ResponseEntity<>(meetingService.getAllTopics(), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllMeetings")
    public ResponseEntity<List<Meeting>> getAllMeetings() {
        return new ResponseEntity<>(meetingService.getAllMeetings(), HttpStatus.OK);
    }

    @GetMapping(value = "/myMeetings")
    public ResponseEntity<List<Meeting>> getMyMeetings(@RequestBody User user) {
        return new ResponseEntity<>(meetingService.getSpeakerMeetings(user), HttpStatus.OK);
    }

}
