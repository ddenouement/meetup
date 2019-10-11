package com.meetup.controller;

import com.meetup.entities.Meeting;
import com.meetup.service.MeetingService;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "meetup-application", description = "Operations used to manage speaker functionality")
public class SpeakerController {

    MeetingService meetingService;

    SpeakerController(@Autowired MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    //TODO AOP (security Config, Filter)
    @PostMapping(value = "/api/v1/user/speaker/meetings")
    public ResponseEntity<Meeting> createMeeting(
        @CookieValue(value = "token", defaultValue = "") String token,
        @RequestBody Meeting meeting) {
        try {
            return new ResponseEntity<>(
                meetingService.createMeeting(meeting, token),
                HttpStatus.CREATED);
        } catch (IllegalAccessException | NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/api/v1/user/speaker/meetings")
    public ResponseEntity<List<Meeting>> getMyMeetings(
        @CookieValue(value = "token", defaultValue = "") String token) {
        try {
            return new ResponseEntity<>(
                meetingService.getSpeakerMeetings(token), HttpStatus.OK);
        } catch (IllegalAccessException | NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
