package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.*;
import com.meetup.entities.dto.ArticleDisplayDTO;
import com.meetup.entities.dto.CommentaryDisplayDTO;
import com.meetup.entities.dto.ComplaintDTO;
import com.meetup.entities.dto.ProfileDTO;
import com.meetup.entities.dto.SimpleUserDTO;

import com.meetup.service.IArticleService;
import com.meetup.service.IBadgeService;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.IMeetupService;
import com.meetup.service.INotificationService;
import com.meetup.service.IProfileService;
import com.meetup.service.ISearchService;
import com.meetup.service.IUserService;
import com.meetup.utils.ModelConstants;
import io.swagger.annotations.Api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * Operations used to manage user functionality.
 */
@RestController
@Api(value = "meetup-application")
@RequestMapping("/api/v1")
public class UserController {

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
     * Operations with badges.
     */
    private IBadgeService badgeService;
    /**
     * Operations with user profile.
     */
    private IProfileService profileService;
    /**
     * Article operations service.
     */
    private IArticleService articleService;
    /**
     * Notification operations.
     */
    private INotificationService notificationService;

    private ISearchService searchService;

    /**
     * Constructor.
     *
     * @param meetupService meetup operations
     * @param userService user operations
     * @param loginValidatorService LoginValidatorService
     * @param badgeService badge operations
     * @param profileService profile operations
     * @param articleService article operations
     * @param notificationService notification operations
     */
    @Autowired
    public UserController(final IMeetupService meetupService,
        final IUserService userService,
        final ILoginValidatorService loginValidatorService,
        final IBadgeService badgeService,
        final IProfileService profileService,
        final ISearchService searchService,
        final IArticleService articleService,
        final INotificationService notificationService) {
        this.meetupService = meetupService;
        this.userService = userService;
        this.loginValidatorService = loginValidatorService;
        this.badgeService = badgeService;
        this.profileService = profileService;
        this.articleService = articleService;
        this.notificationService = notificationService;
        this.searchService = searchService;
    }

    /**
     * Get info about current User.
     *
     * @param token JWT from client
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
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

    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PutMapping(value = "/user/profile")
    public ResponseEntity updateProfile(
        @CookieValue("token") final String token,
        final @RequestBody ProfileDTO profileDTO) {
        userService.updateProfile(profileDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Return all active speakers.
     *
     * @return List of Users
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/speakers")
    public ResponseEntity<List<User>> getAllSpeakers() {
        return new ResponseEntity<>(userService.getAllSpeakers(),
            HttpStatus.OK);
    }

    /**
     * Return all active users.
     *
     * @return List of Users
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "user/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(),
            HttpStatus.OK);
    }

    /**
     * Return all users.
     *
     * @return List of Users
     */
    //TODO edit endpoint name
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "user/users/all")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(),
            HttpStatus.OK);
    }

    /**
     * How users see profile of other users.
     *
     * @param userId id of user, whose profile we want to look at
     * @return ResponseEntity as HashMap
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
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
     * Admin can deactivate user by his Id.
     *
     * @param id user's id
     * @return ResponseEntity
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @PostMapping(value = "/user/users/{id}/deactivate")
    public ResponseEntity deactivateUser(
        @PathVariable("id") final int id) {
        userService.deactivateUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Admin can activate user by his Id.
     *
     * @param id user's id
     * @return status
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @PostMapping(value = "/users/{id}/activate")
    public ResponseEntity activateUser(final @PathVariable("id") int id) {
        userService.activateUser(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * Every user can post a complaint on other. Convert login -> id, convert
     * date in long format -> Date exemplar and pass it to DAO.
     *
     * @param token JSON web token.
     * @param compl complaint entity.
     * @return ResponseEntity.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "/user/complaint")
    public ResponseEntity postComplaintOnUser(
        @CookieValue("token") final String token,
        final @RequestBody ComplaintDTO compl) {
        String login = loginValidatorService.extractLogin(token);
        userService.postComplaintOn(compl, login);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    /**
     * Admin can see all complaints.
     *
     * @return ResponseEntity with list
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @GetMapping(value = "/user/complaints")
    public ResponseEntity getAllComplaints() {
        return new ResponseEntity<>(
            userService.getAllNotReadComplaints(), HttpStatus.OK);
    }

    /**
     * Admin can mark complaint as read.
     *
     * @param complaintID ID of complaint.
     * @return ResponseEntity
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @PostMapping(value = "/user/complaints/{id}/read")
    public ResponseEntity markAsReadComplaint(
        @PathVariable("id") final int complaintID) {
        return new ResponseEntity<>(
            userService.markAsReadComplaint(complaintID), HttpStatus.OK);
    }

    /**
     * User can subscribe to speaker.
     *
     * @param speakerID Speaker ID.
     * @param token JSON web token.
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).SPEAKER, "
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
     * @param token JSON web token.
     * @return ResponseEntity
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).SPEAKER, "
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
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/speakers/{id}/subscribers")
    public ResponseEntity<List<SimpleUserDTO>> getSubscribersOfSpeaker(
        @PathVariable("id") final int speakerID) {
        List<SimpleUserDTO> result_users =
            userService.getSimpleSubscribersOfSpeaker(speakerID);
        return ok(result_users);
    }

    /**
     * Get displayable article
     *
     * @param articleID Article ID.
     * @return Article.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/articles/{id}")
    public ResponseEntity<ArticleDisplayDTO> getArticle(
        @PathVariable("id") final int articleID) {
        return new ResponseEntity<>(
            articleService.getDisplayableArticle(articleID),
            HttpStatus.OK);
    }

    /**
     * Get all displayable articles Article ID.
     *
     * @return Article list.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/articles")
    public ResponseEntity<List<ArticleDisplayDTO>> getArticles() {
        return new ResponseEntity<>(articleService.getAllDisplayableArticles(),
            HttpStatus.OK);
    }

    /**
     * Get comments of specific articles.
     *
     * @param articleID Article ID.
     * @return List of commentaries of article.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "user/articles/{id}/comments")
    public ResponseEntity<List<CommentaryDisplayDTO>> getCommentsOfArticle(
        @PathVariable("id") final int articleID) {
        return new ResponseEntity<>(articleService.getCommentaries(articleID),
            HttpStatus.OK);
    }

    /**
     * Post commentary on article.
     *
     * @param commentary Commentary.
     * @param token JSON web token.
     * @param articleID Article ID.
     * @return Response entity with status code.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "user/articles/{id}/comments")
    public ResponseEntity postCommentary(
        @RequestBody final Commentary commentary,
        @CookieValue("token") final String token,
        @PathVariable("id") final int articleID) {
        String userLogin = loginValidatorService.extractLogin(token);
        articleService.postCommentary(articleID, userLogin, commentary);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Admin can remove article by Id.
     *
     * @param articleID Article ID.
     * @return ResponseEntity with status code.
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @DeleteMapping(value = "/user/articles/{id}")
    public ResponseEntity removeArticle(
        @PathVariable("id") final int articleID) {
        articleService.removeArticleByAdmin(articleID);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Change user's password.
     *
     * @param password new password
     * @param token cookie with JWT
     * @return status
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PutMapping(value = "/user/password")
    public ResponseEntity changePassword(
        @RequestBody final String password,
        @CookieValue("token") final String token) {
        Integer userId = loginValidatorService.extractId(token);
        userService.changePassword(userId, password);
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

    /**
     * Get user's id.
     *
     * @param token cookie with JWT
     * @return user's id
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/id")
    public ResponseEntity<Integer> getUserId(
        @CookieValue("token") final String token) {
        Integer userId = loginValidatorService.extractId(token);
        return ok(userId);
    }

    /**
     * Return all unread notifications for user, sorted in descending order of
     * time created.
     *
     * @param token cookie with JWT
     * @return list of notifications
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/notifications")
    public ResponseEntity<List<Notification>> getNotifications(
        @CookieValue("token") final String token) {
        Integer userId = loginValidatorService.extractId(token);
        return ok(notificationService.findUnread(userId));
    }

    /**
     * Count all unread notifications for user.
     *
     * @param token cookie with JWT
     * @return count of notifications
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/notifications/count")
    public ResponseEntity<Integer> countNotifications(
        @CookieValue("token") final String token) {
        Integer userId = loginValidatorService.extractId(token);
        return ok(notificationService.countUnread(userId));
    }

    /**
     * Mark a notification with specified id as read.
     *
     * @param id id of notification to mark
     * @param token cookie with JWT
     * @return status
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PutMapping(value = "/user/notifications/{id}/read")
    public ResponseEntity markNotificationAsRead(@PathVariable final Integer id,
        @CookieValue("token") final String token) {
        Integer userId = loginValidatorService.extractId(token);
        notificationService.markAsRead(id, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Perform filtered search.
     *
     * @return list of matched meetups
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/users/search")
    public ResponseEntity<List<Meetup>> searchWithFilter(

    ) {
        Filter filter = new Filter();
        //  filter.setRate_to(5);
        filter.setTitle_substring("pt");
        filter.setTopics_ids(Arrays.asList(2, 3));
        filter.setId_language(2);
        filter.setId_user(2);//petrenko (needed only for saving filter)
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy/MM/dd").parse("2019/12/13");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        filter.setTime_to(d);

        return ok(searchService.searchWithFilter(filter));
    }

    //only for testing, to see how JSON looks like
    @GetMapping(value = "/users/filter")
    public ResponseEntity<Filter> getSampleFilter(
    ) {
        Filter filter = new Filter();
        filter.setRate_to(5);
        filter.setTopics_ids(Arrays.asList(2, 3));
        filter.setId_language(2);
        filter.setId_user(2);//petrenko
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy/MM/dd").parse("2019/12/14");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        filter.setTime_to(d);
        return ok(filter);
    }

    /**
     * user can save filter.
     *
     * @param token cookie value
     * @param filter Filter to save
     * @return saved Filter
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "/users/current/filters")
    public ResponseEntity<Filter> saveFilter(
        @CookieValue("token") final String token,
        @RequestBody final Filter filter
    ) {
        int id = loginValidatorService.extractId(token);
        return ok(searchService.insertFilter(filter, id));
    }

    /**
     * Return saved user`s filters.
     *
     * @param token cookie value
     * @return saved Filters list
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/users/current/filters")
    public ResponseEntity<List<Filter>> savedFilters(
        @CookieValue("token") final String token
    ) {
        int id = loginValidatorService.extractId(token);
        return ok(searchService.getUserFilters(id));
    }

    /**
     * Return Role based on id.
     *
     * @return role as String
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/users/{id}/role")
    public ResponseEntity<String> getUserRole(
        @PathVariable("id") final int userId
    ) {
        return new ResponseEntity<>(userService.userPrimaryRole(userId),
            HttpStatus.OK);
    }
}
