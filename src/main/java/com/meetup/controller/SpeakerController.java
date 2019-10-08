package com.meetup.controller;

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

@RestController
@RequestMapping("/api/v1/user/speaker")
@Api(value = "meetup-application", description = "Operations used to manage speaker functionality")
public class SpeakerController {

    @Autowired
    MeetingService meetingService;

    //TODO Find out, if speaker required for meeting creation
    @PostMapping(value = "/createMeeting")
    public ResponseEntity<String> createMeeting(@RequestBody Meeting meeting, User user) {
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
    public ResponseEntity<List<Meeting>> getMyMeetings(@RequestBody User user){
        return new ResponseEntity<>(meetingService.getSpeakerMeetings(user), HttpStatus.OK);
    }


}
