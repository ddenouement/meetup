package com.meetup.service;

import com.meetup.entities.Meetup;
import com.meetup.entities.Notification;
import com.meetup.entities.User;
import java.util.List;

/**
 * Interface to work with notifications in the database.
 */
public interface INotificationService {

    /**
     * Return a notification with specified ID in the database.
     * @param id ID of notification to return
     * @return a notification with specified ID
     */
    Notification findById(Integer id);

    /**
     * Return all unread notifications for user with specified id, sorted in
     * descending order of time created.
     *
     * @param userId id of user to find notifications for
     * @return a list of notifications
     */
    List<Notification> findUnread(Integer userId);

    /**
     * Return the count of all unread notifications for user with specified id.
     * @param userId id of user to count notifications for
     * @return a count of notifications
     */
    Integer countUnread(Integer userId);

    /**
     * Mark a specific notification as read.
     * @param id ID of notification to mark
     * @param userId ID of user who owns notification
     */
    void markAsRead(Integer id, Integer userId);

    /**
     * Insert a notification in the database.
     * @param notification notification to insert
     * @return inserted notification
     */
    Notification insert(Notification notification);

    /**
     * Send a notification of type MEETUP_BOOKED given the corresponding meetup
     * to the meetup's speaker.
     *
     * @param meetup meetup to create notification for
     */
    void sendMeetupBookedNotification(Meetup meetup);

    /**
     * Send a notification of type HOSTED_MEETUP_STARTS_SOON given the
     * corresponding meetup to the meetup's speaker.
     *
     * @param meetup meetup to create notification for
     */
    void sendHostedMeetupStartsSoonNotification(Meetup meetup);

    /**
     * Send notification of type JOINED_MEETUP_STARTS_SOON given the
     * corresponding meetup to all of the meetup's listeners.
     *
     * @param meetup meetup to create notification for
     */
    void sendJoinedMeetupStartsSoonNotifications(Meetup meetup);

    /**
     * Send notification of type MEETUP_INFO_CHANGED given the corresponding
     * meetup and string with change description to all of the meetup's
     * listeners.
     *
     * @param meetup meetup to create notification for
     * @param changes a textual representation of changes
     */
    void sendMeetupInfoChangedNotifications(Meetup meetup, String changes);

    /**
     * Send notification of type NEW_SUBSCRIBED_MEETUP given the corresponding
     * meetup to all of the meetup's speaker subscribers.
     *
     * @param meetup meetup to create notification for
     */
    void sendNewSubscribedMeetupNotifications(Meetup meetup);

    /**
     * Send notification of type LEAVE_FEEDBACK given the corresponding meetup
     * to all of the meetup's listeners.
     *
     * @param meetup meetup to create notification for
     */
    void sendLeaveFeedbackNotifications(Meetup meetup);

    /**
     * Send notification of type PROFILE_DEACTIVATED to the user with given id.
     *
     * @param userId id of user to send notification to
     */
    void sendProfileDeactivatedNotification(Integer userId);

    /**
     * Send notification of type PROFILE_ACTIVATED to the user with given id.
     *
     * @param userId id of user to send notification to
     */
    void sendProfileActivatedNotification(Integer userId);
}
