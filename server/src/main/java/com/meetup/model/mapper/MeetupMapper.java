package com.meetup.model.mapper;

import com.meetup.entities.Meetup;
import com.meetup.utils.TimeUtility;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.meetup.utils.constants.DbQueryConstants;
import org.springframework.jdbc.core.RowMapper;

/**
 * . Class, used to map ResultSet from DB to Meetup model.
 */
public final class MeetupMapper implements RowMapper<Meetup> {

    /**
     * . Callback method of RowMapper interface.
     *
     * @param rs A table of data representing a database result set.
     * @param rowNum the number of the current row
     * @return Meetup object for the current row
     * @throws SQLException Exception due to SQL
     */
    public Meetup mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        Meetup meetup = new Meetup();
        meetup.setId(rs.getInt(DbQueryConstants.id.name()));
        meetup.setSpeakerId(rs.getInt(DbQueryConstants.id_speaker.name()));
        meetup.setLanguageId(rs.getInt(DbQueryConstants.id_language.name()));
        meetup.setTopicId(rs.getInt(DbQueryConstants.id_topic.name()));
        meetup.setStateId(rs.getInt(DbQueryConstants.id_state.name()));
        meetup.setTitle(rs.getString(DbQueryConstants.title.name()));
        meetup.setStartDate(
            TimeUtility.convertToLocalDateTime(rs.getTimestamp(DbQueryConstants.start_time.name())));
        meetup.setDurationMinutes(rs.getInt(DbQueryConstants.duration_minutes.name()));
        meetup.setMinAttendees(rs.getInt(DbQueryConstants.min_attendees.name()));
        meetup.setMaxAttendees(rs.getInt(DbQueryConstants.max_attendees.name()));
        meetup.setDescription(rs.getString(DbQueryConstants.description.name()));
        return meetup;
    }
}
