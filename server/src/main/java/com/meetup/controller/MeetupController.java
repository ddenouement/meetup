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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * . API Rest Controller for Meetups
 */
@RestController
@Api(value = "meetup-application")
@RequestMapping("/api/v1")
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
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/meetups")
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
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/meetups/{id}")
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
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping("/meetups/topics")
    public ResponseEntity<List<Topic>> getAvailableTopics() {
        return new ResponseEntity<>(meetupService.getAllTopics(),
            HttpStatus.OK);
    }

    /**
     * Get topic.
     * @param topicID topic ID.
     * @return Response entity with topic.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping("/meetups/topics/{id}")
    public ResponseEntity<Topic> getTopic(
        @PathVariable("id") final int topicID) {
        return new ResponseEntity<>(meetupService.getTopic(topicID),
            HttpStatus.OK);
    }

    /**
     * Create topic.
     * @param topic Created topic.
     * @return Response entity with topic.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping("/meetups/topics")
    public ResponseEntity<Topic> createTopic(
        @RequestBody final Topic topic) {
        return new ResponseEntity<>(meetupService.createTopic(topic),
            HttpStatus.CREATED);
    }

    /**
     * Update topic.
     * @param topicID topic ID to be edited.
     * @param topic edited topic.
     * @return Response entity with topic.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PutMapping("/meetups/topics/{id}")
    public ResponseEntity<Topic> updateTopic(
        @PathVariable("id") final int topicID,
        @RequestBody final Topic topic) {
        return new ResponseEntity<>(meetupService.updateTopic(topicID, topic),
            HttpStatus.OK);
    }

    /**
     * Remove topic.
     * @param topicID topic ID to be removed.
     * @return Response entity with status code.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @DeleteMapping("/meetups/topics/{id}")
    public ResponseEntity removeTopic(
        @PathVariable("id") final int topicID) {
        meetupService.removeTopic(topicID);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    /**
     * Retrieve meetups of speaker.
     *
     * @param speakerID Speaker ID.
     * @return Response entity with list of meetups.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/meetups/speakers/{id}")
    public ResponseEntity<List<MeetupDisplayDTO>> getSpeakerMeetups(
        @PathVariable("id") final int speakerID) {
        return new ResponseEntity<>(
            meetupService.getSpeakerMeetups(speakerID), HttpStatus.OK);
    }
}
