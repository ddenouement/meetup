package com.meetup.controller;

import com.meetup.entities.Topic;
import com.meetup.entities.dto.MeetupDisplayDTO;
import com.meetup.service.impl.LoginValidatorServiceImpl;
import com.meetup.service.impl.MeetupServiceImpl;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * . API Rest Controller for Meetups
 */
@RestController
@Api(value = "meetup-application")
public class MeetupController {

    /**
     * . Service, that manages meetup functionality
     */
    private MeetupServiceImpl meetupService;
    /**
     * Login validator service service.
     */
    private LoginValidatorServiceImpl loginValidatorService;

    /**
     * . set the MeetupService
     *
     * @param meetupService MeetupService custom
     * @param loginValidatorService LoginValidatorService.
     */
    MeetupController(@Autowired final MeetupServiceImpl meetupService,
        @Autowired final LoginValidatorServiceImpl loginValidatorService) {
        this.meetupService = meetupService;
        this.loginValidatorService = loginValidatorService;
    }

    /**
     * .
     *
     * @return ResponseEntity<List               <               Meetup>>
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
        + "T(com.meetup.entities.Role).SPEAKER, "
        + "T(com.meetup.entities.Role).LISTENER)")
    @GetMapping(value = "api/v1/meetups")
    public ResponseEntity<List<MeetupDisplayDTO>> getAllMeetups() {
        return new ResponseEntity<>(meetupService.getAllMeetups(),
            HttpStatus.OK);
    }


    /**
     * Get existing meetup.
     *
     * @param meetupID Meetup ID.
     * @return Response entity with meetup.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
        + "T(com.meetup.entities.Role).SPEAKER, "
        + "T(com.meetup.entities.Role).LISTENER)")
    @GetMapping(value = "/api/v1/meetups/{id}")
    public ResponseEntity<MeetupDisplayDTO> getMeetup(
        @PathVariable("id") final int meetupID) {
        return new ResponseEntity<>(meetupService.getMeetup(meetupID),
            HttpStatus.OK);
    }

    /**
     * Get all topics mapping.
     *
     * @return Response entity with list of all topics.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
        + "T(com.meetup.entities.Role).SPEAKER, "
        + "T(com.meetup.entities.Role).LISTENER)")
    @GetMapping("api/v1/meetups/topics")
    public ResponseEntity<List<Topic>> getAvailableTopics() {
        return new ResponseEntity<>(meetupService.getAllTopics(),
            HttpStatus.OK);
    }

    /**
     * Retrieve meetups of speaker.
     *
     * @param speakerID Speaker ID.
     * @return Response entity with list of meetups.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
        + "T(com.meetup.entities.Role).SPEAKER, "
        + "T(com.meetup.entities.Role).LISTENER)")
    @GetMapping(value = "/api/v1/meetups/speaker/{id}")
    public ResponseEntity<List<MeetupDisplayDTO>> getSpeakerMeetups(
        @PathVariable("id") final int speakerID) {
        return new ResponseEntity<>(
            meetupService.getSpeakerMeetups(speakerID), HttpStatus.OK);
    }
}
