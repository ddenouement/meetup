package com.meetup.repository.impl;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import com.meetup.model.mapper.MeetingMapper;
import com.meetup.repository.IMeetingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dmytro Zubko
 */

@Repository
@PropertySource("classpath:sql/meetup_queries.properties")
public class MeetingDaoImpl implements IMeetingDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Value("${get_all_meetings}")
    private String GET_ALL_MEETINGS;
    @Value("${get_speaker_meetings}")
    private String GET_SPEAKER_MEETINGS;
    @Value("${insert_new_meeting}")
    private String INSERT_NEW_MEETING;
    @Value("${find_topic_id}")
    private String FIND_TOPIC_ID_BY_NAME;
    @Value("${add_topic_to_meeting}")
    private String ADD_TOPIC_TO_MEETING;

    @Override
    public List<Meeting> getAllMeetings() {
        return this.template.query(GET_ALL_MEETINGS, new MeetingMapper());
    }

    @Override
    public void insertNewMeeting(Meeting meeting) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_speaker", meeting.getSpeakerId())
                .addValue("id_language", meeting.getLanguageId())
                .addValue("title", meeting.getTitle())
                .addValue("start_time", meeting.getDate())
                .addValue("min_atendees", meeting.getMinAttendees())
                .addValue("max_atendees", meeting.getMaxAttendees())
                .addValue("description", meeting.getDescription());
        template.update(INSERT_NEW_MEETING, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            meeting.setId(holder.getKey().intValue());
            //adding topics to DB
            //TODO rewrite
            for (Topic topic : meeting.getTopics()) {
                addTopicToMeeting(meeting, topic);
            }
        }
    }

    @Override
    public void addTopicToMeeting(Meeting meeting, Topic topic) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("name", topic.getName());
        Integer topic_id = template.queryForObject(FIND_TOPIC_ID_BY_NAME, namedParameters, Integer.class);

        Map parametersForAddingTopic = new HashMap();
        parametersForAddingTopic.put("id_meetup", meeting.getId());
        parametersForAddingTopic.put("id_topic", topic_id);
        template.update(ADD_TOPIC_TO_MEETING, parametersForAddingTopic);
    }

    @Override
    public List<Meeting> getSpeakerMeetings(int speakerID) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_speaker", speakerID);
        return this.template.query(GET_SPEAKER_MEETINGS, param, new MeetingMapper());
    }
}
