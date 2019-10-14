package com.meetup.controller;

import com.meetup.entities.Meetup;
import com.meetup.service.IMeetupService;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "meetup-application", description = "Operations used to manage speaker functionality")
public class SpeakerController {

    private IMeetupService IMeetupService;


    SpeakerController(@Autowired IMeetupService meetupService) {
        this.IMeetupService = meetupService;
    }


    @PreAuthorize("hasRole('SPEAKER')")
    @PostMapping(value = "/api/v1/user/speaker/meetups")
    public ResponseEntity<Meetup> createMeetup(
        @CookieValue(value = "token", defaultValue = "") String token,
        @RequestBody Meetup meetup) {
        try {
            return new ResponseEntity<>(
                IMeetupService.createMeetup(meetup, token),
                HttpStatus.CREATED);
        } catch (IllegalAccessException | NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasRole('SPEAKER')")
    @PutMapping(value = "/api/v1/user/speaker/meetups/{id}")
    public ResponseEntity<Meetup> updateMeetup(
        @CookieValue(value = "token", defaultValue = "") String token,
        @RequestBody Meetup meetup) {
        try {
            return new ResponseEntity<>(
                IMeetupService.updateMeetup(meetup, token),
                HttpStatus.OK);
        } catch (IllegalAccessException | NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasRole('SPEAKER')")
    @GetMapping(value = "/api/v1/user/speaker/meetups")
    public ResponseEntity<List<Meetup>> getMyMeetups(
        @CookieValue(value = "token", defaultValue = "") String token) {
        try {
            return new ResponseEntity<>(
                IMeetupService.getSpeakerMeetups(token), HttpStatus.OK);
        } catch (IllegalAccessException | NullPointerException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
