package com.meetup.controller;

import com.meetup.entities.Meetup;
import com.meetup.service.impl.LoginValidatorServiceImpl;
import com.meetup.service.impl.MeetupServiceImpl;
import io.swagger.annotations.Api;
import java.util.List;
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
     * Login validator service service.
     */
    private LoginValidatorServiceImpl loginValidatorService;

    /**
     * SpeakerController constructor.
     *
     * @param meetupService MeetupService param.
     * @param loginValidatorService LoginValidatorService.
     */
    SpeakerController(@Autowired final MeetupServiceImpl meetupService,
        @Autowired final LoginValidatorServiceImpl loginValidatorService) {
        this.meetupService = meetupService;
        this.loginValidatorService = loginValidatorService;
    }

    /**
     * Create meetup.
     *
     * @param token JSON web token.
     * @param meetup Meetup object to be created.
     * @return Created Meetup.
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).SPEAKER)")
    @PostMapping(value = "/api/v1/user/speaker/meetups")
    public ResponseEntity createMeetup(
        @CookieValue("token") final String token,
        @RequestBody final Meetup meetup) {
        String userLogin = loginValidatorService.extractLogin(token);
        meetupService.createMeetup(meetup, userLogin);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Update existing meetup.
     *
     * @param token JSON web token.
     * @param meetup Meetup to be updated.
     * @return Response entity with updated meetup.
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).SPEAKER)")
    @PutMapping(value = "/api/v1/user/speaker/meetups/{id}")
    public ResponseEntity<Meetup> updateMeetup(
        @CookieValue("token") final String token,
        @RequestBody final Meetup meetup) {
        String userLogin = loginValidatorService.extractLogin(token);
        meetupService.updateMeetup(meetup, userLogin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retrieve meetups of speaker.
     *
     * @param token JSON web token.
     * @return Response entity with list of meetups.
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).SPEAKER)")
    @GetMapping(value = "/api/v1/user/speaker/meetups")
    public ResponseEntity<List<Meetup>> getMyMeetups(
        @CookieValue("token") final String token) {
        String userLogin = loginValidatorService.extractLogin(token);
        return new ResponseEntity<>(
            meetupService.getSpeakerMeetups(userLogin), HttpStatus.OK);
    }
}
