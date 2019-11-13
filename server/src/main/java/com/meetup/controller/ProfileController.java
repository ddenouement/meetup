package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.Meetup;
import com.meetup.entities.dto.ProfileDTO;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.IMeetupService;
import com.meetup.service.IProfileService;
import com.meetup.service.IUserService;
import com.meetup.utils.constants.ModelConstants;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Rest Controller for Profile.
 */
@RestController
@Api(value = "meetup-application")
@RequestMapping("/api/v1")
public class ProfileController {

    /**
     * Operations with meetups.
     */
    private IMeetupService meetupService;
    /**
     * Operations with users.
     */
    private IUserService userService;
    /**
     * Login validation service.
     */
    private ILoginValidatorService loginValidatorService;
    /**
     * Operations with user profile.
     */
    private IProfileService profileService;


    /**
     * Constructor.
     *
     * @param meetupService meetup operations
     * @param userService user operations
     * @param loginValidatorService LoginValidatorService
     * @param profileService profile operations
     */
    @Autowired
    public ProfileController(final IMeetupService meetupService,
        final IUserService userService,
        final ILoginValidatorService loginValidatorService,
        final IProfileService profileService) {
        this.meetupService = meetupService;
        this.userService = userService;
        this.loginValidatorService = loginValidatorService;
        this.profileService = profileService;
    }

    /**
     * Get info about current User.
     *
     * @param token JWT from client
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/profile")
    public ResponseEntity getUserProfile(
        @CookieValue("token") final String token) {
        int id = loginValidatorService.extractId(token);
        Map<Object, Object> model =
            profileService.getOtherUserProfile(id);
        if (model.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        //user can see his own future joined meetups
        List<Meetup> userJoinedMeetupsFuture =
            meetupService.getJoinedMeetupsFuture(id);
        model.put(ModelConstants.joinedMeetupsFuture, userJoinedMeetupsFuture);
        return ok(model);
    }

    /**
     * How users see profile of other users.
     *
     * @param userId id of user, whose profile we want to look at
     * @return ResponseEntity as HashMap
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/people/profile/{id}")
    public ResponseEntity getOtherUserProfile(
        @PathVariable("id") final int userId) {
        Map<Object, Object> model = profileService.getOtherUserProfile(userId);
        if (model.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ok(model);

    }

    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PutMapping(value = "/user/profile")
    public ResponseEntity updateProfile(
        @CookieValue("token") final String token,
        final @RequestBody ProfileDTO profileDTO) {
        userService.updateProfile(profileDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
