package com.meetup.service.impl;

import com.meetup.entities.Notification;
import com.meetup.repository.INotificationDAO;
import com.meetup.service.INotificationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements INotificationService {

    /** Notification repository. */
    private INotificationDAO notificationDAO;

    /**
     * Initialize service.
     * @param notificationDAO notification repository.
     */
    @Autowired
    public NotificationServiceImpl(final INotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    /**
     * Return a notification with specified ID in the database.
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
     * @param userId id of user to count notifications for
     * @return a count of notifications
     */
    @Override
    public Integer countUnread(final Integer userId) {
        return notificationDAO.countUnread(userId);
    }

    /**
     * Mark a specific notification as read.
     * @param id ID of notification to mark
     * @param userId ID of user who owns notification
     */
    @Override
    public void markAsRead(final Integer id, final Integer userId) {
        notificationDAO.markAsRead(id, userId);
    }

    /**
     * Insert a notification in the database.
     * @param notification notification to insert
     * @return inserted notification
     */
    @Override
    public Notification insert(final Notification notification) {
        return notificationDAO.insert(notification);
    }
}
