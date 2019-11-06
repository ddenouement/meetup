package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.dto.NotificationDTO;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.INotificationService;
import com.meetup.utils.NotificationDTOConverter;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Rest Controller for Notifications.
 */
@RestController
@Api(value = "meetup-application")
@RequestMapping("/api/v1")
public class NotificationController {

    /**
     * Login validation service.
     */
    private ILoginValidatorService loginValidatorService;
    /**
     * Notification operations.
     */
    private INotificationService notificationService;


    /**
     * Constructor.
     *
     * @param loginValidatorService LoginValidatorService
     * @param notificationService notification operations
     */
    @Autowired
    public NotificationController(
        final INotificationService notificationService,
        final ILoginValidatorService loginValidatorService) {
        this.notificationService = notificationService;
        this.loginValidatorService = loginValidatorService;
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
    public ResponseEntity<List<NotificationDTO>> getNotifications(
        @CookieValue("token") final String token) {
        Integer userId = loginValidatorService.extractId(token);
        List<NotificationDTO> notificationDTOs = notificationService
            .findUnread(userId)
            .stream()
            .map(NotificationDTOConverter::convert)
            .collect(Collectors.toList());
        return ok(notificationDTOs);
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
}
