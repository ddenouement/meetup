package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.User;
import com.meetup.entities.dto.*;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.IUserService;
import com.meetup.utils.constants.ModelConstants;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Operations used to manage user functionality.
 */
@RestController
@Api(value = "meetup-application")
@RequestMapping("/api/v1")
public class UserController {

    /**
     * Operations with users.
     */
    private IUserService userService;
    /**
     * Login validation service.
     */
    private ILoginValidatorService loginValidatorService;


    /**
     * Constructor.
     *
     * @param userService           user operations
     * @param loginValidatorService LoginValidatorService
     */
    @Autowired
    public UserController(final IUserService userService,
                          final ILoginValidatorService loginValidatorService) {
        this.userService = userService;
        this.loginValidatorService = loginValidatorService;
    }

    /**
     * Return all active users.
     *
     * @return List of Users
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN)")
    @GetMapping(value = "user/users")
    public ResponseEntity<List<User>> getAllActiveUsers() {
        return new ResponseEntity<>(userService.getAllActiveUsers(),
                HttpStatus.OK);
    }

    /**
     * Return all users.
     *
     * @return List of Users
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN)")
    @GetMapping(value = "user/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(),
                HttpStatus.OK);
    }

    /**
     * Return all users with number of complaints.
     *
     * @return List of UsersComplaintsDTO
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN)")
    @GetMapping(value = "user/users/all", params = {"pagesize", "page"})
    public ResponseEntity getAllUsersWithComplaintsCount(
            @RequestParam("pagesize") final int pageSize,
            @RequestParam("page") final int currentPage) {
        int offset = pageSize * (currentPage - 1);
        int count = userService.getAllUsersCount();
        Map<Object, Object> model = new HashMap<>();
        List<UserComplaintsDTO> users_complaints = userService.getAllUsersWithComplaints(pageSize, offset);
        model.put(ModelConstants.usersCount, count);
        model.put(ModelConstants.users, users_complaints);
        return new ResponseEntity<>(model, HttpStatus.OK);

    }

    /**
     * Return all active speakers.
     *
     * @return List of Users
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
            + "T(com.meetup.utils.Role).SPEAKER, "
            + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/speakers")
    public ResponseEntity<List<User>> getAllSpeakers() {
        return new ResponseEntity<>(userService.getAllSpeakers(),
                HttpStatus.OK);
    }

    /**
     * Get user's id.
     *
     * @param token cookie with JWT
     * @return user's id
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
            + "T(com.meetup.utils.Role).SPEAKER, "
            + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/id")
    public ResponseEntity<Integer> getUserId(
            @CookieValue("token") final String token) {
        Integer userId = loginValidatorService.extractId(token);
        return ok(userId);
    }

    /**
     * Return Role based on id.
     *
     * @return role as String
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
            + "T(com.meetup.utils.Role).SPEAKER, "
            + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/users/{id}/role")
    public ResponseEntity<String> getUserRole(
            @PathVariable("id") final int userId
    ) {
        return new ResponseEntity<>(userService.userPrimaryRole(userId),
                HttpStatus.OK);
    }

    /**
     * Return login based on id.
     *
     * @return role as String
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
            + "T(com.meetup.utils.Role).SPEAKER, "
            + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/users/current/login")
    public ResponseEntity<String> getUserLogin(
            @CookieValue("token") final String token
    ) {
        int userId = loginValidatorService.extractId(token);
        return new ResponseEntity<>(
                userService.getProfileUserDTO(userId).getLogin(),
                HttpStatus.OK);
    }

    /**
     * Every user can post a complaint on other. Convert login -> id, convert
     * date in long format -> Date exemplar and pass it to DAO.
     *
     * @param token JSON web token.
     * @param compl complaint entity.
     * @return ResponseEntity.
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).SPEAKER, "
            + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "/user/complaint")
    public ResponseEntity postComplaintOnUser(
            @CookieValue("token") final String token,
            final @RequestBody ComplaintDTO compl) {
        String login = loginValidatorService.extractLogin(token);
        System.out.println(login);
        userService.postComplaintOn(compl, login);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    /**
     * User can subscribe to speaker.
     *
     * @param speakerID Speaker ID.
     * @param token     JSON web token.
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).SPEAKER, "
            + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "/user/speakers/{id}/subscribe")
    public ResponseEntity subscribeToSpeaker(
            @CookieValue("token") final String token,
            @PathVariable("id") final int speakerID) {
        int userID = loginValidatorService.extractId(token);
        userService.subscribeToSpeaker(userID, speakerID);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * User can unsubscribe from speaker.
     *
     * @param speakerID Speaker ID.
     * @param token     JSON web token.
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).SPEAKER, "
            + "T(com.meetup.utils.Role).LISTENER)")
    @DeleteMapping(value = "/user/speakers/{id}/subscribe")
    public ResponseEntity unsubscribeFromSpeaker(
            @CookieValue("token") final String token,
            @PathVariable("id") final int speakerID) {
        int userID = loginValidatorService.extractId(token);
        userService.unSubscribeFromSpeaker(userID, speakerID);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Get simplified users who are active & are subscribed on given speaker.
     *
     * @param speakerID Speaker ID.
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).SPEAKER, "
            + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/speakers/{id}/subscribers")
    public ResponseEntity<List<SimpleUserDTO>> getSubscribersOfSpeaker(
            @PathVariable("id") final int speakerID) {
        List<SimpleUserDTO> result_users =
                userService.getSimpleSubscribersOfSpeaker(speakerID);
        return ok(result_users);
    }
    /**
     * .
     *@param userId user id
     * @return List of complaints to user
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN)")
    @GetMapping(value = "/users/{id}/complaints")
    public ResponseEntity<List<ComplaintDTO>> getComplaintsToUser(@PathVariable("id") final int userId) {
        List<ComplaintDTO> result_complaints =
                userService.getComplaintsToUser(userId);
        return ok(result_complaints);
    }


}
