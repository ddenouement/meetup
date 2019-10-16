package com.meetup.controller;

import com.meetup.entities.Meetup;
import com.meetup.service.impl.MeetupServiceImpl;
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

/**
 * API Rest Controller for speaker functionality.
 */
@RestController
@Api(value = "meetup-application", description = "Operations used to manage speaker functionality")
public class SpeakerController {

    /**
     * Meetup service.
     */
    private MeetupServiceImpl meetupService;

    /**
     * SpeakerController constructor.
     * @param meetupService
     * MeetupService param.
     */
    SpeakerController(@Autowired final MeetupServiceImpl meetupService) {
        this.meetupService = meetupService;
    }

    /**
     * Create meetup.
     * @param token
     * JSON web token.
     * @param meetup
     * Meetup object to be created.
     * @return
     * Created Meetup.
     */
    @PreAuthorize("hasRole('SPEAKER')")
    @PostMapping(value = "/api/v1/user/speaker/meetups")
    public ResponseEntity<Meetup> createMeetup(
        @CookieValue(value = "token", defaultValue = "")
            final String token, @RequestBody final Meetup meetup) {
        try {
            return new ResponseEntity<>(
                meetupService.createMeetup(meetup, token),
                HttpStatus.CREATED);
        } catch (IllegalAccessException | NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Update existing meetup.
     * @param token
     * JSON web token.
     * @param meetup
     * Meetup to be updated.
     * @return
     * Response entity with updated meetup.
     */
    @PreAuthorize("hasRole('SPEAKER')")
    @PutMapping(value = "/api/v1/user/speaker/meetups/{id}")
    public ResponseEntity<Meetup> updateMeetup(
        @CookieValue(value = "token", defaultValue = "")
        final String token, @RequestBody final Meetup meetup) {
        try {
            return new ResponseEntity<>(
                meetupService.updateMeetup(meetup, token),
                HttpStatus.OK);
        } catch (IllegalAccessException | NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Retrieve meetups of speaker.
     * @param token
     * JSON web token.
     * @return
     * Response entity with list of meetups.
     */
    @PreAuthorize("hasRole('SPEAKER')")
    @GetMapping(value = "/api/v1/user/speaker/meetups")
    public ResponseEntity<List<Meetup>> getMyMeetups(
        @CookieValue(value = "token", defaultValue = "")
        final String token) {
        try {
            return new ResponseEntity<>(
                meetupService.getSpeakerMeetups(token), HttpStatus.OK);
        } catch (IllegalAccessException | NullPointerException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
