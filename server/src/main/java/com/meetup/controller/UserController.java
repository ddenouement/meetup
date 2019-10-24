package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;
import com.meetup.entities.Meetup;
import com.meetup.entities.User;
import com.meetup.entities.dto.ComplaintDTO;
import com.meetup.entities.dto.UserDTO;
import com.meetup.service.IBadgeService;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.IMeetupService;
import com.meetup.service.IProfileService;
import com.meetup.service.IUserService;
import com.meetup.utils.ModelConstants;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.Map;
import com.meetup.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
     * . Operations with user profile
     */
    private IProfileService profileService;

    /**
     * Constructor.
     * @param meetupService
     * MeetupService.
     * @param userService
     * UserService.
     * @param loginValidatorService
     * LoginValidatorService
     * @param badgeService badge operations
     * @param profileService profile operations
     */
    @Autowired
    public UserController(final IMeetupService meetupService,
                          final IUserService userService,
                          final ILoginValidatorService loginValidatorService,
                          final IBadgeService badgeService,
                          final IProfileService profileService) {
        this.meetupService = meetupService;
        this.userService = userService;
        this.loginValidatorService = loginValidatorService;
        this.badgeService = badgeService;
        this.profileService = profileService;
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
        UserDTO user = userService
                .getProfileUserDTO(loginValidatorService.extractLogin(token));
        Map<Object, Object> model =
                profileService.getOtherUserProfile(user.getLogin());
        if (model.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        //user can see his own future joined meetups
        Pair<List<Meetup>, List<Meetup>> joined =
                meetupService.getUserJoinedMeetups(user.getId());
        List<Meetup> userJoinedMeetupsFuture = joined.getSecond();
        model.put(ModelConstants.JOINED_MEETUPS_FUTURE, userJoinedMeetupsFuture);

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

        Map<Object, Object> model = profileService.getOtherUserProfile(login);
        if (model.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
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
      return new ResponseEntity<>("Done", HttpStatus.OK);

    }
    /**.
    * Every user can post a complaint on other
     * @param compl complaint entity
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
            + "T(com.meetup.entities.Role).SPEAKER, "
            + "T(com.meetup.entities.Role).LISTENER)")
    @PostMapping(value = "/api/v1/user/complaint")
    public ResponseEntity postComplaintOnUser(
            @CookieValue("token") final String token,
            final @RequestBody ComplaintDTO compl) {
        String login = loginValidatorService.extractLogin(token);
        userService.postComplaintOn(compl, login);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
    /**.
     * Admin can see all complaints
     * @return ResponseEntity with list
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).ADMIN)")
    @GetMapping(value = "/api/v1/user/complaints")
    public ResponseEntity getAllComplaints() {
        return new ResponseEntity<>(
                userService.getAllComplaints(), HttpStatus.OK);
    }
    /**.
     * Admin can mark complaint as read
     * @return ResponseEntity
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).ADMIN)")
    @PostMapping(value = "/api/v1/user/complaints/read/{id}")
    public ResponseEntity markAsReadComplaint(
            @PathVariable("id") final int complaintID) {
        return new ResponseEntity<>(
                userService.markAsReadComplaint(complaintID), HttpStatus.OK);
    }

}
