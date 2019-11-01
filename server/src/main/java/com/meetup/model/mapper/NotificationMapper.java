package com.meetup.model.mapper;

import com.meetup.entities.Notification;
import com.meetup.entities.NotificationType;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * . custom RowMapper
 */
public final class NotificationMapper implements RowMapper<Notification> {

    /**
     * Map a row from a result set to an instance of class Language.
     *
     * @param rs result set
     * @param rowNum number of row to map
     * @return Badge created from specified row
     * @throws SQLException if an SQLException is encountered getting column
     * values
     */
    public Notification mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        Notification notification = new Notification();
        notification.setId(rs.getInt("id"));
        notification.setMessage(rs.getString("message"));
        notification.setIdUser(rs.getInt("id_user"));
        notification.setRead(rs.getBoolean("read"));
        notification.setType(NotificationType.valueOf(rs.getString("type")));
        notification.setTimeCreated(
            TimeUtility
                .convertToLocalDateTime(rs.getTimestamp("time_created")));
        return notification;
    }
}
