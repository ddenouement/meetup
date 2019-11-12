package com.meetup.model.mapper;

import com.meetup.entities.Language;
import com.meetup.entities.Topic;
import com.meetup.entities.dto.MeetupDisplayDTO;
import com.meetup.entities.dto.SimpleUserDTO;
import com.meetup.utils.constants.DbQueryConstants;
import com.meetup.utils.MeetupState;
import com.meetup.utils.TimeUtility;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Class, used to map ResultSet from DB to MeetupDisplayDTO.
 */
public final class MeetupDisplayDtoMapper implements RowMapper<MeetupDisplayDTO> {

    /**
     * Callback method of RowMapper interface.
     *
     * @param rs A table of data representing a database result set.
     * @param rowNum the number of the current row
     * @return MeetupDisplayDTO object for the current row
     * @throws SQLException Exception due to SQL
     */
    public MeetupDisplayDTO mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        Language language = new Language();
        language.setId(rs.getInt(DbQueryConstants.id_language.name()));
        language.setName(rs.getString(DbQueryConstants.language_name.name()));

        Topic topic = new Topic();
        topic.setId(rs.getInt(DbQueryConstants.id_topic.name()));
        topic.setName(rs.getString(DbQueryConstants.topic_name.name()));

        SimpleUserDTO speaker = new SimpleUserDTO();
        speaker.setId(rs.getInt(DbQueryConstants.id_speaker.name()));
        speaker.setLogin(rs.getString(DbQueryConstants.speaker_login.name()));
        speaker.setFirstName(rs.getString(DbQueryConstants.speaker_first_name.name()));
        speaker.setLastName(rs.getString(DbQueryConstants.speaker_last_name.name()));
        speaker.setRate(rs.getFloat(DbQueryConstants.speaker_rate.name()));
        speaker.setNumRates(rs.getInt(DbQueryConstants.speaker_num_rates.name()));

        MeetupDisplayDTO meetup = new MeetupDisplayDTO();
        meetup.setId(rs.getInt(DbQueryConstants.id.name()));
        meetup.setSpeaker(speaker);
        meetup.setLanguage(language);
        meetup.setTopic(topic);
        meetup.setState(MeetupState.getStateByID(rs.getInt(DbQueryConstants.id_state.name())));
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
