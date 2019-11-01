package com.meetup.repository;

import com.meetup.entities.Notification;
import java.util.List;

/**
 * Interface to work with notifications in the database.
 */
public interface INotificationDAO {

    /**
     * Return a notification with specified ID in the database.
     * @param id ID of notification to return
     * @return a notification with specified ID
     */
    Notification findById(Integer id);

    /**
     * Return all unread notifications for user with specified id,
     * sorted in descending order of time created.
     * @param userId id of user to find notifications for
     * @return a list of notifications
     */
    List<Notification> findAll(Integer userId);

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
}
