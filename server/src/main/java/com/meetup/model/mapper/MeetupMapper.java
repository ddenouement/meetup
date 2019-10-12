package com.meetup.model.mapper;

import com.meetup.entities.Meetup;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.RowMapper;

public final class MeetupMapper implements RowMapper<Meetup> {

    public final Meetup mapRow(ResultSet rs, int rowNum) throws SQLException {
        Meetup meetup = new Meetup();
        meetup.setId(rs.getInt("id"));
        meetup.setSpeakerId(rs.getInt("id_speaker"));
        meetup.setLanguageId(rs.getInt("id_language"));
        meetup.setState(rs.getInt("id_state"));
        meetup.setTitle(rs.getString("title"));
        meetup.setDate(
            convertToLocalDateTimeViaSqlTimestamp(rs.getDate("start_time")));
        meetup.setMinAttendees(rs.getInt("min_atendees"));
        meetup.setMaxAttendees(rs.getInt("max_atendees"));
        meetup.setDescription(rs.getString("description"));
        return meetup;
    }

    private LocalDateTime convertToLocalDateTimeViaSqlTimestamp(
        Date dateToConvert) {
        return new java.sql.Timestamp(
            dateToConvert.getTime()).toLocalDateTime();
    }
}
