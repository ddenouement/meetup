package com.meetup.controller;

import static com.meetup.controller.ModelConstants.BADGES;
import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.Meetup;
import com.meetup.entities.User;
import com.meetup.entities.dto.UserDTO;
import com.meetup.service.IBadgeService;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.IMeetupService;
import com.meetup.service.IUserService;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meetup.entities.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.meetup.controller.ModelConstants.JOINED_MEETUPS_PAST;
import static com.meetup.controller.ModelConstants.JOINED_MEETUPS_FUTURE;
import static com.meetup.controller.ModelConstants.HOSTED_MEETUPS_FUTURE;
import static com.meetup.controller.ModelConstants.HOSTED_MEETUPS_PAST;
import static com.meetup.controller.ModelConstants.USERDTO;
import static com.meetup.controller.ModelConstants.SUBSCRIPTIONS;

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
    /** Operations with badges. */
    private IBadgeService badgeService;

    /**
     * Constructor.
     * @param meetupService
     * MeetupService.
     * @param userService
     * UserService.
     * @param loginValidatorService
     * LoginValidatorService
     */
    @Autowired
    public UserController(final IMeetupService meetupService,
                          final IUserService userService,
                          final ILoginValidatorService loginValidatorService,
                          final IBadgeService badgeService) {
        this.meetupService = meetupService;
        this.userService = userService;
        this.loginValidatorService = loginValidatorService;
        this.badgeService = badgeService;
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
        model.put(USERDTO, user);
        model.put(SUBSCRIPTIONS, userService.getUsersSubscriptionsToSpeakers(
                user.getId()));
        Pair<List<Meetup>, List<Meetup>> hosted =
                meetupService.getSpeakerMeetupsByLogin(user.getLogin());
        List<Meetup> hostedMeetupsPast = hosted.getFirst();
        List<Meetup> hostedMeetupsFuture = hosted.getSecond();
        model.put(HOSTED_MEETUPS_FUTURE, hostedMeetupsFuture);
        model.put(HOSTED_MEETUPS_PAST, hostedMeetupsPast);
        Pair<List<Meetup>, List<Meetup>> joined =
                meetupService.getUserJoinedMeetups(user.getId());
        List<Meetup> userJoinedMeetupsPast = joined.getFirst();
        List<Meetup> userJoinedMeetupsFuture = joined.getSecond();
        model.put(JOINED_MEETUPS_PAST, userJoinedMeetupsPast);
        model.put(JOINED_MEETUPS_FUTURE, userJoinedMeetupsFuture);
        List<String> badges = badgeService.getUserBadges(user.getId());
        model.put(BADGES, badges);
        return ok(model);

    }

    /**
     * . return all active speakers
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

    /**.
     * How   users see profile of other users
     * @param login login of user, whose profile we want to look at
     * @return ResponseEntity as HashMap
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
            + "T(com.meetup.entities.Role).SPEAKER, "
            + "T(com.meetup.entities.Role).LISTENER)")
    @GetMapping(value = "/api/v1/user/people/profile")
    public ResponseEntity getOtherUserProfile(
            final @RequestParam String login) {
        Map<Object, Object> model = new HashMap<>();
        UserDTO user = userService.getProfileUserDTO(login);
        model.put(USERDTO, user);
        model.put(SUBSCRIPTIONS, userService.getUsersSubscriptionsToSpeakers(
                user.getId()));
        Pair<List<Meetup>, List<Meetup>> hosted =
                meetupService.getSpeakerMeetupsByLogin(user.getLogin());
        List<Meetup> hostedMeetupsPast = hosted.getFirst();
        List<Meetup> hostedMeetupsFuture = hosted.getSecond();
        model.put(HOSTED_MEETUPS_FUTURE, hostedMeetupsFuture);
        model.put(HOSTED_MEETUPS_PAST, hostedMeetupsPast);
        //we don`t send future joined, as part of privacy
        Pair<List<Meetup>, List<Meetup>> joined =
                meetupService.getUserJoinedMeetups(user.getId());
        List<Meetup> userJoinedMeetupsPast = joined.getFirst();
        model.put(JOINED_MEETUPS_PAST, userJoinedMeetupsPast);
        List<String> badges = badgeService.getUserBadges(user.getId());
        model.put(BADGES, badges);
        return ok(model);

    }

    /**
     * Join user to meeetup.
     * @param meetupID Meetup, that user should join.
     * @param token JSON web token.
     * @return Response entity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
        + "T(com.meetup.entities.Role).SPEAKER, "
        + "T(com.meetup.entities.Role).LISTENER)")
    @PostMapping("api/v1/user/meetups/{id}")
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
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
        + "T(com.meetup.entities.Role).SPEAKER, "
        + "T(com.meetup.entities.Role).LISTENER)")
    @DeleteMapping("api/v1/user/meetups/{id}")
    public ResponseEntity leaveMeetup(
        @CookieValue("token") final String token,
        @PathVariable("id") final int meetupID) {
        String userLogin = loginValidatorService.extractLogin(token);
        meetupService.leaveMeetup(meetupID, userLogin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**.
     * Admin can deactivate user by his Id
     * @param id user's id
     * @return ResponseEntity
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).ADMIN)")
    @PostMapping(value = "/api/v1/user/deactivateUser")
    public ResponseEntity deactivateUser(final @RequestParam int id) {
      userService.deactivateUser(id);
      return new ResponseEntity<>(
                "Done", HttpStatus.OK);

    }

}
