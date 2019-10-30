package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.controller.jwtsecurity.JwtTokenFilter;
import com.meetup.controller.jwtsecurity.JwtTokenProvider;
import com.meetup.entities.Meetup;
import com.meetup.entities.User;
import com.meetup.entities.dto.ComplaintDTO;
import com.meetup.entities.dto.SimpleUserDTO;
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
     * . get info about current User.
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
        List<Meetup> userJoinedMeetupsFuture =
                meetupService.getJoinedMeetupsFuture(user.getId());
        model.put(ModelConstants.joinedMeetupsFuture, userJoinedMeetupsFuture);
        return ok(model);
    }

    /**
     * Return all active speakers.
     * @return List of Users
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
            + "T(com.meetup.entities.Role).SPEAKER, "
            + "T(com.meetup.entities.Role).LISTENER)")
    @GetMapping(value = "/api/v1/user/speakers")
    public ResponseEntity<List<User>> getAllSpeakers() {
        return new ResponseEntity<>(userService.getAllSpeakers(),
                HttpStatus.OK);
    }

    /**
     * Return all active users.
     * @return List of Users
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
        + "T(com.meetup.entities.Role).SPEAKER, "
        + "T(com.meetup.entities.Role).LISTENER)")
    @GetMapping(value = "/api/v1/user/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(),
            HttpStatus.OK);
    }

    /**
     * How users see profile of other users.
     * @param userId login of user, whose profile we want to look at
     * @return ResponseEntity as HashMap
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
            + "T(com.meetup.entities.Role).SPEAKER, "
            + "T(com.meetup.entities.Role).LISTENER)")
    @GetMapping(value = "/api/v1/user/people/profile")
    public ResponseEntity getOtherUserProfile(
            final @PathVariable String userId) {
        Map<Object, Object> model = profileService.getOtherUserProfile(userId);
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
     * Remove user from meeetup.     *
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

    /**
     * Admin can deactivate user by his Id.
     * @param id user's id
     * @return ResponseEntity
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).ADMIN)")
    @PostMapping(value = "/api/v1/user/deactivateUser")
    public ResponseEntity deactivateUser(final @RequestParam int id) {
      userService.deactivateUser(id);
      return new ResponseEntity<>("Done", HttpStatus.OK);

    }
    /**
    * Every user can post a complaint on other.
     *Convert login -> id, convert date in long format -> Date exemplar and pass it to DAO
     * @param compl complaint entity
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).ADMIN, "
            + "T(com.meetup.entities.Role).SPEAKER, "
            + "T(com.meetup.entities.Role).LISTENER)")
    @PostMapping(value = "/api/v1/user/complain")
    public ResponseEntity postComplaintOnUser(
            @CookieValue("token") final String token,
            final @RequestBody ComplaintDTO compl) {
        String login = loginValidatorService.extractLogin(token);
        userService.postComplaintOn(compl, login);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
    /**
     * Admin can see all complaints.
     * @return ResponseEntity with list
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).ADMIN)")
    @GetMapping(value = "/api/v1/user/complaints")
    public ResponseEntity getAllComplaints() {
        return new ResponseEntity<>(
                userService.getAllNotReadComplaints(), HttpStatus.OK);
    }
    /**
     * Admin can mark complaint as read.
     * @return ResponseEntity
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).ADMIN)")
    @PostMapping(value = "/api/v1/user/complaints/read/{id}")
    public ResponseEntity markAsReadComplaint(
            @PathVariable("id") final int complaintID) {
        return new ResponseEntity<>(
                userService.markAsReadComplaint(complaintID), HttpStatus.OK);
    }

    /**
     * User can subscribe to speaker.
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).SPEAKER, "
            + "T(com.meetup.entities.Role).LISTENER)")
    @PostMapping(value = "/api/v1/user/subscriptions/{id}")
    public ResponseEntity subscribeToSpeaker(
            @CookieValue("token") final String token,
            @PathVariable("id") final int speakerID) {
        int userID = loginValidatorService.extractId(token);
        userService.subscribeToSpeaker(userID, speakerID);
        return new ResponseEntity(HttpStatus.OK);
    }
    /**
     * User can unsubscribe from speaker.
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).SPEAKER, "
            + "T(com.meetup.entities.Role).LISTENER)")
    @DeleteMapping(value = "/api/v1/user/subscriptions/{id}")
    public ResponseEntity unsubscribeFromSpeaker(
            @CookieValue("token") final String token,
            @PathVariable("id") final int speakerID) {
        int userID = loginValidatorService.extractId(token);
        userService.unSubscribeFromSpeaker(userID, speakerID);
        return new ResponseEntity(HttpStatus.OK);
    }
    /**
     * Get simplified users who are active & are subscribed on given speaker
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.entities.Role).SPEAKER, "
            + "T(com.meetup.entities.Role).LISTENER)")
    @GetMapping(value = "/api/v1/user/speakers/{id}/subscribers")
    public ResponseEntity<List <SimpleUserDTO> > getSubscribersOfSpeaker(
            @PathVariable("id") final int speakerID) {
       List <SimpleUserDTO> result_users =
               userService.getSimpleSubscribersOfSpeaker(speakerID);
        return ok(result_users);
    }

}
