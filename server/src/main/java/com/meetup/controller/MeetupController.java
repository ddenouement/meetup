package com.meetup.controller;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
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
    private IMeetupService IMeetupService;

    /**.
     * set the MeetupService
     * @param IMeetupService MeetupService custom
     */
    MeetupController(@Autowired IMeetupService IMeetupService) {
        this.IMeetupService = IMeetupService;
    }

    /**.
     *
     * @param token String
     * @return ResponseEntity<List<Meetup>>
     */
    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @GetMapping(value = "api/v1/meetups")
    public ResponseEntity<List<Meetup>> getAllMeetups(
        @CookieValue(value = "token", defaultValue = "") String token) {
        try {
            return new ResponseEntity<>(IMeetupService.getAllMeetups(),
                HttpStatus.OK);
        } catch (NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @GetMapping("api/v1/meetups/topics")
    public ResponseEntity<List<Topic>> getAvailableTopics(
        @CookieValue(value = "token", defaultValue = "") String token) {
        try {
            return new ResponseEntity<>(IMeetupService.getAllTopics(),
                HttpStatus.OK);
        } catch (NullPointerException | NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
