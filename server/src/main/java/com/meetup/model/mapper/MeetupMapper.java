package com.meetup.model.mapper;

import com.meetup.entities.Meetup;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.RowMapper;

/**.
 * Class, used to map ResultSet from DB to Meetup model.
 */
public final class MeetupMapper implements RowMapper<Meetup> {

    /**.
     * Callback method of RowMapper interface.
     * @param rs
     * A table of data representing a database result set.
     * @param rowNum
     * the number of the current row
     * @return
     * Meetup object for the current row
     * @throws SQLException
     * Exception due to SQL
     */
    public Meetup mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        Meetup meetup = new Meetup();
        meetup.setId(rs.getInt("id"));
        meetup.setSpeakerId(rs.getInt("id_speaker"));
        meetup.setLanguageId(rs.getInt("id_language"));
        meetup.setStateId(rs.getInt("id_state"));
        meetup.setTitle(rs.getString("title"));
        meetup.setStartDate(
            convertToLocalDateTimeViaSqlTimestamp(rs.getDate("start_time")));
        meetup.setDurationMinutes(rs.getInt("duration_minutes"));
        meetup.setMinAttendees(rs.getInt("min_attendees"));
        meetup.setMaxAttendees(rs.getInt("max_attendees"));
        meetup.setDescription(rs.getString("description"));
        return meetup;
    }

    /**.
     * Helper method to convert SQL Date to LocalDateTime.
     * @param dateToConvert
     * SQL Date value
     * @return
     * LocalDateTime object
     */
    private LocalDateTime convertToLocalDateTimeViaSqlTimestamp(
        final Date dateToConvert) {
        return new java.sql.Timestamp(
            dateToConvert.getTime()).toLocalDateTime();
    }
}
