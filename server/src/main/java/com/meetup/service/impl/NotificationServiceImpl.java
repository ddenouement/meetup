package com.meetup.service.impl;

import com.meetup.entities.Meetup;
import com.meetup.entities.Notification;
import com.meetup.entities.User;
import com.meetup.repository.INotificationDAO;
import com.meetup.service.IMeetupService;
import com.meetup.service.INotificationService;
import com.meetup.service.IUserService;
import com.meetup.utils.NotificationConstants;
import com.meetup.utils.NotificationType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements INotificationService {

    /**
     * Notification repository.
     */
    private INotificationDAO notificationDAO;

    /**
     * Meetup operations.
     */
    private IMeetupService meetupService;

    /**
     * User operations.
     */
    private IUserService userService;

    /**
     * Initialize service.
     *
     * @param notificationDAO notification repository.
     * @param meetupService meetup operations.
     * @param userService user operations.
     */
    @Autowired
    public NotificationServiceImpl(final INotificationDAO notificationDAO,
        @Lazy final IMeetupService meetupService,
        @Lazy final IUserService userService) {
        this.notificationDAO = notificationDAO;
        this.meetupService = meetupService;
        this.userService = userService;
    }

    /**
     * Return a notification with specified ID in the database.
     *
     * @param id ID of notification to return
     * @return a notification with specified ID
     */
    @Override
    public Notification findById(final Integer id) {
        return notificationDAO.findById(id);
    }

    /**
     * Return all unread notifications for user with specified id, sorted in
     * descending order of time created.
     *
     * @param userId id of user to find notifications for
     * @return a list of notifications
     */
    @Override
    public List<Notification> findUnread(final Integer userId) {
        return notificationDAO.findUnread(userId);
    }

    /**
     * Return the count of all unread notifications for user with specified id.
     *
     * @param userId id of user to count notifications for
     * @return a count of notifications
     */
    @Override
    public Integer countUnread(final Integer userId) {
        return notificationDAO.countUnread(userId);
    }

    /**
     * Mark a specific notification as read.
     *
     * @param id ID of notification to mark
     * @param userId ID of user who owns notification
     */
    @Override
    public void markAsRead(final Integer id, final Integer userId) {
        notificationDAO.markAsRead(id, userId);
    }

    /**
     * Insert a notification in the database.
     *
     * @param notification notification to insert
     * @return inserted notification
     */
    @Override
    public Notification insert(final Notification notification) {
        return notificationDAO.insert(notification);
    }

    /**
     * Send a notification of type MEETUP_BOOKED given the corresponding meetup
     * to the meetup's speaker.
     *
     * @param meetup meetup to create notification for
     */
    public void sendMeetupBookedNotification(final Meetup meetup) {
        String message = NotificationConstants.MEETUP_BOOKED_MESSAGE
            .replace(NotificationConstants.ROUTER_LINK_PLACEHOLDER,
                NotificationConstants.MEETUP_ROUTER_LINK)
            .replace(NotificationConstants.ID_PLACEHOLDER,
                String.valueOf(meetup.getId()))
            .replace(NotificationConstants.TEXT_PLACEHOLDER, meetup.getTitle());
        Notification notification = new Notification(message,
            NotificationType.MEETUP_BOOKED);
        sendNotificationToUser(notification, meetup.getSpeakerId());
    }

    /**
     * Send a notification of type HOSTED_MEETUP_STARTS_SOON given the
     * corresponding meetup to the meetup's speaker.
     *
     * @param meetup meetup to create notification for
     */
    public void sendHostedMeetupStartsSoonNotification(final Meetup meetup) {
        String message = NotificationConstants.HOSTED_MEETUP_STARTS_SOON_MESSAGE
            .replace(NotificationConstants.ROUTER_LINK_PLACEHOLDER,
                NotificationConstants.MEETUP_ROUTER_LINK)
            .replace(NotificationConstants.ID_PLACEHOLDER,
                String.valueOf(meetup.getId()))
            .replace(NotificationConstants.TEXT_PLACEHOLDER, meetup.getTitle());
        Notification notification = new Notification(message,
            NotificationType.HOSTED_MEETUP_STARTS_SOON);
        sendNotificationToUser(notification, meetup.getSpeakerId());
    }

    /**
     * Send notification of type JOINED_MEETUP_STARTS_SOON given the
     * corresponding meetup to all of the meetup's listeners.
     *
     * @param meetup meetup to create notification for
     */
    public void sendJoinedMeetupStartsSoonNotifications(final Meetup meetup) {
        String message = NotificationConstants.JOINED_MEETUP_STARTS_SOON_MESSAGE
            .replace(NotificationConstants.ROUTER_LINK_PLACEHOLDER,
                NotificationConstants.MEETUP_ROUTER_LINK)
            .replace(NotificationConstants.ID_PLACEHOLDER,
                String.valueOf(meetup.getId()))
            .replace(NotificationConstants.TEXT_PLACEHOLDER, meetup.getTitle());
        Notification notification = new Notification(message,
            NotificationType.JOINED_MEETUP_STARTS_SOON);
        List<User> listeners = meetupService.getUsersOnMeetup(meetup.getId());
        for (User listener : listeners) {
            sendNotificationToUser(notification, listener.getId());
        }
    }

    /**
     * Send notification of type MEETUP_INFO_CHANGED given the corresponding
     * meetup and string with change description to all of the meetup's
     * listeners.
     *
     * @param meetup meetup to create notification for
     * @param changes a textual representation of changes
     */
    public void sendMeetupInfoChangedNotifications(final Meetup meetup,
        final String changes) {
        String message = NotificationConstants.MEETUP_INFO_CHANGED_MESSAGE_START
            .replace(NotificationConstants.ROUTER_LINK_PLACEHOLDER,
                NotificationConstants.MEETUP_ROUTER_LINK)
            .replace(NotificationConstants.ID_PLACEHOLDER,
                String.valueOf(meetup.getId()))
            .replace(NotificationConstants.TEXT_PLACEHOLDER, meetup.getTitle())
            + changes;
        Notification notification = new Notification(message,
            NotificationType.MEETUP_INFO_CHANGED);
        meetupService.getUsersOnMeetup(meetup.getId())
            .forEach(u -> sendNotificationToUser(notification, u.getId()));
    }

    /**
     * Send notification of type NEW_SUBSCRIBED_MEETUP given the corresponding
     * meetup to all of the meetup's speaker subscribers.
     *
     * @param meetup meetup to create notification for
     */
    public void sendNewSubscribedMeetupNotifications(final Meetup meetup) {
        String message = NotificationConstants.NEW_SUBSCRIBED_MEETUP_MESSAGE
            .replace(NotificationConstants.ROUTER_LINK_PLACEHOLDER,
                NotificationConstants.MEETUP_ROUTER_LINK)
            .replace(NotificationConstants.ID_PLACEHOLDER,
                String.valueOf(meetup.getId()))
            .replace(NotificationConstants.TEXT_PLACEHOLDER, meetup.getTitle());
        Notification notification = new Notification(message,
            NotificationType.NEW_SUBSCRIBED_MEETUP);
        userService.getSimpleSubscribersOfSpeaker(meetup.getSpeakerId())
            .forEach(u -> sendNotificationToUser(notification, u.getId()));
    }

    /**
     * Send notification of type LEAVE_FEEDBACK given the corresponding meetup
     * to all of the meetup's listeners.
     *
     * @param meetup meetup to create notification for
     */
    public void sendLeaveFeedbackNotifications(final Meetup meetup) {
        String message = NotificationConstants.LEAVE_FEEDBACK_MESSAGE
            .replace(NotificationConstants.MEETUP_TITLE_PLACEHOLDER,
                meetup.getTitle())
            .replace(NotificationConstants.ROUTER_LINK_PLACEHOLDER,
                NotificationConstants.FEEDBACK_ROUTER_LINK)
            .replace(NotificationConstants.ID_PLACEHOLDER,
                String.valueOf(meetup.getId()))
            .replace(NotificationConstants.TEXT_PLACEHOLDER,
                NotificationConstants.FEEDBACK_LINK_TEXT);
        Notification notification = new Notification(message,
            NotificationType.LEAVE_FEEDBACK);
        meetupService.getUsersOnMeetup(meetup.getId())
            .forEach(u -> sendNotificationToUser(notification, u.getId()));
    }

    /**
     * Send notification of type PROFILE_DEACTIVATED to the user with given id.
     *
     * @param userId id of user to send notification to
     */
    public void sendProfileDeactivatedNotification(final Integer userId) {
        Notification notification = new Notification(
            NotificationConstants.PROFILE_DEACTIVATED_MESSAGE,
            NotificationType.PROFILE_DEACTIVATED);
        sendNotificationToUser(notification, userId);
    }

    /**
     * Send notification of type PROFILE_ACTIVATED to the user with given id.
     *
     * @param userId id of user to send notification to
     */
    public void sendProfileActivatedNotification(final Integer userId) {
        Notification notification = new Notification(
            NotificationConstants.PROFILE_ACTIVATED_MESSAGE,
            NotificationType.PROFILE_ACTIVATED);
        sendNotificationToUser(notification, userId);
    }

    /**
     * Send a given notification to user with given id.
     *
     * @param notification notification to insert
     * @param userId id of user to send the notification to
     */
    private void sendNotificationToUser(
        final Notification notification, final Integer userId) {
        notification.setIdUser(userId);
        insert(notification);
        // TODO: notify client.
    }


}
