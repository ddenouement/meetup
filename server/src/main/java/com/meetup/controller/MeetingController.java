package com.meetup.controller;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import com.meetup.service.MeetingService;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "meetup-application", description = "Operations used to manage meeting functionality")
public class MeetingController {

    MeetingService meetingService;

    MeetingController(@Autowired MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @GetMapping(value = "api/v1/meetings")
    public ResponseEntity<List<Meeting>> getAllMeetings(
        @CookieValue(value = "token", defaultValue = "") String token) {
        try {
            return new ResponseEntity<>(meetingService.getAllMeetings(token),
                HttpStatus.OK);
        } catch (NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @GetMapping("api/v1/meetings/topics")
    public ResponseEntity<List<Topic>> getAvailableTopics(
        @CookieValue(value = "token", defaultValue = "") String token) {
        try {
            return new ResponseEntity<>(meetingService.getAllTopics(token),
                HttpStatus.OK);
        } catch (NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
