package com.meetup.controller;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**.
 * API Rest Controller for Meetups
 */
@RestController
@Api(value = "meetup-application")
public class MeetupController {

    /**.
     * Service, that manages meetup functionality
     */
    private MeetupServiceImpl meetupService;

    /**.
     * set the MeetupService
     * @param meetupService MeetupService custom
     */
    MeetupController(@Autowired final MeetupServiceImpl meetupService) {
        this.meetupService = meetupService;
    }

    /**.
     *
     * @param token String
     * @return ResponseEntity<List<Meetup>>
     */
    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @GetMapping(value = "api/v1/meetups")
    public ResponseEntity<List<Meetup>> getAllMeetups(
        @CookieValue(value = "token", defaultValue = "")
            final String token) {
        try {
            return new ResponseEntity<>(meetupService.getAllMeetups(),
                HttpStatus.OK);
        } catch (NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all topics mapping.
     * @param token
     * JSON web token.
     * @return
     * Response entity with list of all topics.
     */
    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @GetMapping("api/v1/meetups/topics")
    public ResponseEntity<List<Topic>> getAvailableTopics(
        @CookieValue(value = "token", defaultValue = "")
        final String token) {
        try {
            return new ResponseEntity<>(meetupService.getAllTopics(),
                HttpStatus.OK);
        } catch (NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Join user to meeetup.
     * @param token
     * JSON web token.
     * @param meetup
     * Meetup, that user should join.
     * @return
     * Response entity
     */
    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @PostMapping("api/v1/meetups/join")
    public ResponseEntity joinMeetup(
        @CookieValue(value = "token", defaultValue = "")
        final String token, @RequestBody final Meetup meetup) {
        try {
            meetupService.joinMeetup(meetup, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IndexOutOfBoundsException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Remove user from meeetup.
     * @param token
     * JSON web token.
     * @param meetup
     * Meetup, that user should leave.
     * @return
     * Response entity
     */
    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @PostMapping("api/v1/meetups/leave")
    public ResponseEntity leaveMeetup(
        @CookieValue(value = "token", defaultValue = "")
        final String token, @RequestBody final Meetup meetup) {
        try {
            meetupService.leaveMeetup(meetup, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
