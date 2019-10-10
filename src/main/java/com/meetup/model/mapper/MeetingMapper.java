package com.meetup.model.mapper;

import com.meetup.entities.Meeting;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public final class MeetingMapper implements RowMapper<Meeting> {
    public final Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
        Meeting meeting = new Meeting();
        meeting.setId(rs.getInt("id"));
        meeting.setSpeakerId(rs.getInt("id_speaker"));
        meeting.setLanguageId(rs.getInt("id_language"));
        meeting.setState(rs.getInt("id_state"));
        meeting.setTitle(rs.getString("title"));
        meeting.setDate(convertToLocalDateTimeViaSqlTimestamp(rs.getDate("start_time")));
        meeting.setMinAttendees(rs.getInt("min_atendees"));
        meeting.setMaxAttendees(rs.getInt("max_atendees"));
        meeting.setDescription(rs.getString("description"));
        return meeting;
    }

    private LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }
}
