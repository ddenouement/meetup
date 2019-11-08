package com.meetup.repository.impl;

import com.meetup.entities.Notification;
import com.meetup.model.mapper.CountMapper;
import com.meetup.model.mapper.NotificationMapper;
import com.meetup.repository.INotificationDAO;
import com.meetup.utils.DbQueryConstants;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Implemetation of INotificationDAO.
 */
@Repository
@PropertySource("classpath:sql/notification_queries.properties")
public class NotificationDaoImpl implements INotificationDAO {

    /**
     * Provides JDBC operations with named parameters.
     **/
    private NamedParameterJdbcTemplate template;

    /**
     * Initialize with the instance of NamedParameterJdbcTemplate.
     *
     * @param template template to use to perform JDBC operations
     */
    @Autowired
    public NotificationDaoImpl(final NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    /**
     * SQL script to select all rows in table notifications with specific
     * user_id, read = false and sorted by time_created in descending order.
     */
    @Value("${find_all_notifications}")
    private String findAllNotifications;

    /**
     * SQL script to count all rows in table notifications with specific user_id
     * and read = false.
     */
    @Value("${count_all_notifications}")
    private String countAllNotifications;

    /**
     * SQL script to select a row with specific id in table notifications.
     */
    @Value("${find_notification_by_id}")
    private String findNotificationById;

    /**
     * SQL script to insert a row in table notifications.
     */
    @Value("${insert_notification}")
    private String insertNotification;

    /**
     * SQL script to change column read to TRUE in the table notifications.
     */
    @Value("${mark_notification_as_read}")
    private String markNotificationAsRead;

    /**
     * Return all unread notifications for user with specified id, sorted in
     * descending order of time created.
     *
     * @param userId id of user to find notifications for
     * @return a list of notifications
     */
    @Override
    public List<Notification> findUnread(final Integer userId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_user.name(), userId);
        return template
            .query(findAllNotifications, param, new NotificationMapper());
    }

    /**
     * Return the count of all unread notifications for user with specified id.
     * @param userId id of user to count notifications for
     * @return a count of notifications
     */
    @Override
    public Integer countUnread(final Integer userId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_user.name(), userId);
        List<Integer> counts = template
            .query(countAllNotifications, param, new CountMapper());
        if (counts.isEmpty()) {
            return null;
        } else {
            return counts.get(0);
        }
    }

    /**
     * Return a notification with specified ID in the database.
     *
     * @param id ID of notification to return
     * @return a notification with specified ID
     */
    @Override
    public Notification findById(final Integer id) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), id);
        List<Notification> notifications = template
            .query(findNotificationById, param, new NotificationMapper());
        if (notifications.isEmpty()) {
            return null;
        } else {
            return notifications.get(0);
        }
    }

    /**
     * Mark a specific notification as read.
     *
     * @param id ID of notification to mark
     * @param userId ID of user who owns notification
     */
    @Override
    public void markAsRead(final Integer id, final Integer userId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), id)
            .addValue(DbQueryConstants.id_user.name(), userId);
        template.update(markNotificationAsRead, param);
    }

    /**
     * Insert a notification in the database.
     *
     * @param notification notification to insert
     * @return inserted notification
     */
    @Override
    public Notification insert(final Notification notification) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.message.name(), notification.getMessage())
            .addValue(DbQueryConstants.id_user.name(), notification.getIdUser())
            .addValue(DbQueryConstants.read.name(), notification.getRead())
            .addValue(DbQueryConstants.id_type.name(), notification.getType().getId())
            .addValue(DbQueryConstants.time_created.name(), notification.getTimeCreated());
        template.update(insertNotification, param, holder, new String[]{"id"});
        notification.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return notification;
    }
}
