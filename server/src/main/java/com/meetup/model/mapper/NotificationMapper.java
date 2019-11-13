package com.meetup.model.mapper;

import com.meetup.entities.Notification;
import com.meetup.utils.NotificationType;
import com.meetup.utils.constants.DbQueryConstants;
import com.meetup.utils.TimeUtility;
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
        notification.setId(rs.getInt(DbQueryConstants.id.name()));
        notification.setMessage(rs.getString(DbQueryConstants.message.name()));
        notification.setIdUser(rs.getInt(DbQueryConstants.id_user.name()));
        notification.setRead(rs.getBoolean(DbQueryConstants.read.name()));
        notification.setType(NotificationType.valueOf(rs.getString(DbQueryConstants.type.name())));
        notification.setTimeCreated(
            TimeUtility
                .convertToLocalDateTime(rs.getTimestamp(DbQueryConstants.time_created.name())));
        return notification;
    }
}
