package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.Meetup;
import com.meetup.entities.User;
import com.meetup.entities.dto.UserDTO;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.IMeetupService;
import com.meetup.service.IUserService;
import io.swagger.annotations.Api;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Operations used to manage user functionality
 */
@RestController
@Api(value = "meetup-application")
public class UserController {

    /**
     * . Operations with meetups
     */
    private IMeetupService meetupService;
    /**
     * . Operations with users
     */
    private IUserService userService;
    /**
     * Login validation service.
     */
    private ILoginValidatorService loginValidatorService;

    @Autowired
    public UserController(final IMeetupService meetupService,
        final IUserService userService,
        final ILoginValidatorService loginValidatorService) {
        this.meetupService = meetupService;
        this.userService = userService;
        this.loginValidatorService = loginValidatorService;
    }

    /**
     * . get info about User
     *
     * @param token JWT from client
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
        + "T(com.meetup.entities.Role).SPEAKER, "
        + "T(com.meetup.entities.Role).LISTENER)")
    @GetMapping(value = "/api/v1/user/profile")
    public ResponseEntity getUserProfile(
        @CookieValue("token") final String token) {
        Map<Object, Object> model = new HashMap<>();
        UserDTO user = userService
            .getProfileUserDTO(loginValidatorService.extractLogin(token));
        model.put("UserDTO", user);
        //TODO to Constants;
        List<Meetup> hostedMeetups =
            meetupService.getSpeakerMeetupsByLogin(user.getLogin());
        model.put("hostedMeetups", hostedMeetups);
        model.put("subscribedToSpeakers",
            userService.getUsersSubscriptionsToSpeakers(user.getId()));
        // model.put("filters", );
        model.put("joinedMeetups",
            meetupService.getUserJoinedMeetups(user.getId()));
        return ok(model);

    }

    /**
     * . return all speakers
     *
     * @return List of Users
     */
    //TODOo implement
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
        + "T(com.meetup.entities.Role).SPEAKER, "
        + "T(com.meetup.entities.Role).LISTENER)")
    @GetMapping(value = "/api/v1/user/availableSpeakers")
    public ResponseEntity<List<User>> getAllSpeakers() {
        return new ResponseEntity<>(userService.getAllSpeakers(),
            HttpStatus.OK);
    }

}
