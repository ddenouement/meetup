package com.meetup.controller;

import com.meetup.entities.Feedback;
import com.meetup.entities.Meetup;
import com.meetup.entities.dto.MeetupDisplayDTO;
import com.meetup.service.IMeetupService;
import com.meetup.service.IUserService;
import com.meetup.service.impl.LoginValidatorServiceImpl;
import com.meetup.service.impl.MeetupServiceImpl;
import com.meetup.service.impl.UserServiceImpl;
import com.meetup.utils.ModelConstants;
import io.swagger.annotations.Api;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private IMeetupService meetupService;
    /**
     * Service, that manages user functionality.
     */
    private IUserService userService;
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
    @Autowired
    MeetupController(final MeetupServiceImpl meetupService,
        final UserServiceImpl userService,
        final LoginValidatorServiceImpl loginValidatorService) {
        this.meetupService = meetupService;
        this.userService = userService;
        this.loginValidatorService = loginValidatorService;
    }

    /**
     * .
     *
     * @return ResponseEntity<List < Meetup>>
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
     * .
     *
     * @return ResponseEntity<List < Meetup>>
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/meetups", params = {"pagesize", "page"})
    public ResponseEntity getMeetupsByPages(
        @RequestParam("pagesize") final int pageSize,
        @RequestParam("page") final int currentPage) {
        int offset = pageSize * (currentPage - 1);
        int count = meetupService.getAllMeetupsCount();
        List<MeetupDisplayDTO> meetups = meetupService.getMeetupsByPages(offset, pageSize);
        Map<Object, Object> model = new HashMap<>();
        model.put(ModelConstants.meetupCount, count);
        model.put(ModelConstants.meetups, meetups);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /**
     * Get existing meetup.
     *
     * @param meetupId Meetup ID.
     * @return Response entity with meetup.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/meetups/{id}")
    public ResponseEntity getMeetup(
            @CookieValue("token") final String token,
            @PathVariable("id") final int meetupId) {
        int userId = loginValidatorService.extractId(token);
        MeetupDisplayDTO meetup = meetupService.getMeetup(meetupId);
        boolean ifJoinedMeetup = meetupService.ifJoinedMeetup(userId, meetupId);
        Map<Object,Object> model = new HashMap<>();
        model.put(ModelConstants.ifJoinedMeetup, ifJoinedMeetup);
        model.put(ModelConstants.meetup, meetup);
        return new ResponseEntity<>(model, HttpStatus.OK);
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

    /**
     * Create meetup.
     *
     * @param token JSON web token.
     * @param meetup Meetup object to be created.
     * @return ResponseEntity with status code.
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).SPEAKER)")
    @PostMapping(value = "/user/speaker/meetups")
    public ResponseEntity<Meetup> createMeetup(
        @CookieValue("token") final String token,
        @RequestBody final Meetup meetup) {
        String userLogin = loginValidatorService.extractLogin(token);
        return new ResponseEntity<>(
            meetupService.createMeetup(meetup, userLogin), HttpStatus.CREATED);
    }

    /**
     * Update existing meetup.
     *
     * @param token JSON web token.
     * @param updatedMeetup Meetup to be updated.
     * @param meetupID Meetup ID to be canceled.
     * @return Response entity with status code.
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).SPEAKER)")
    @PutMapping(value = "/user/speaker/meetups/{id}")
    public ResponseEntity<Meetup> updateMeetup(
        @CookieValue("token") final String token,
        @RequestBody final Meetup updatedMeetup,
        @PathVariable("id") final int meetupID) {
        String userLogin = loginValidatorService.extractLogin(token);
        return new ResponseEntity<>(
            meetupService.updateMeetup(meetupID, updatedMeetup, userLogin),
            HttpStatus.OK);
    }

    /**
     * Cancel existing meetup.
     *
     * @param token JSON web token.
     * @param meetupID Meetup to be canceled.
     * @return Response entity with status code.
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).SPEAKER)")
    @DeleteMapping(value = "/user/speaker/meetups/{id}")
    public ResponseEntity<Meetup> cancelMeetup(
        @CookieValue("token") final String token,
        @PathVariable("id") final int meetupID) {
        String userLogin = loginValidatorService.extractLogin(token);
        return new ResponseEntity<>(
            meetupService.cancelMeetup(meetupID, userLogin), HttpStatus.OK);
    }

    /**
     * Start meetup by speaker.
     *
     * @param token JSON web token.
     * @param meetupID Meetup ID that should be started.
     * @return ResponseEntity with status code.
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).SPEAKER)")
    @PostMapping(value = "/user/speaker/meetups/{id}/start")
    public ResponseEntity<Meetup> startMeetup(
        @CookieValue("token") final String token,
        @PathVariable("id") final int meetupID) {
        return new ResponseEntity<>(
            meetupService.startMeetup(meetupID), HttpStatus.OK);
    }

    /**
     * Terminate meetup by speaker.
     *
     * @param token JSON web token.
     * @param meetupID Meetup ID that should be terminated.
     * @return ResponseEntity with status code.
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).SPEAKER)")
    @PostMapping(value = "/user/speaker/meetups/{id}/terminate")
    public ResponseEntity<Meetup> terminateMeetup(
        @CookieValue("token") final String token,
        @PathVariable("id") final int meetupID) {
        return new ResponseEntity<>(
            meetupService.terminateMeetup(meetupID), HttpStatus.OK);
    }

    /**
     * Join user to meeetup.
     *
     * @param meetupID Meetup, that user should join.
     * @param token JSON web token.
     * @return Response entity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping("/user/meetups/{id}")
    public ResponseEntity joinMeetup(
        @CookieValue("token") final String token,
        @PathVariable("id") final int meetupID) {
        String userLogin = loginValidatorService.extractLogin(token);
        meetupService.joinMeetup(meetupID, userLogin);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Remove user from meeetup.
     *
     * @param token JSON web token.
     * @param meetupID Meetup, that user should leave.
     * @return Response entity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @DeleteMapping("/user/meetups/{id}")
    public ResponseEntity leaveMeetup(
        @CookieValue("token") final String token,
        @PathVariable("id") final int meetupID) {
        String userLogin = loginValidatorService.extractLogin(token);
        meetupService.leaveMeetup(meetupID, userLogin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Rate specific meetup.
     *
     * @param feedback Feedback of user.
     * @param token JSON web token.
     * @param meetupID Meetup ID.
     * @return Response entity with status code.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "/user/rate/meetups/{id}")
    public ResponseEntity rateMeetup(
        @RequestBody final Feedback feedback,
        @CookieValue("token") final String token,
        @PathVariable("id") final int meetupID) {
        String userLogin = loginValidatorService.extractLogin(token);
        userService.rateMeetup(meetupID, userLogin, feedback);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
