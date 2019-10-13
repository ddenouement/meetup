package com.meetup.repository.impl;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.model.mapper.MeetupMapper;
import com.meetup.repository.IMeetupDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:sql/meetup_queries.properties")
public class MeetupDaoImpl implements IMeetupDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Value("${get_all_meetings}")
    private String GET_ALL_MEETUPS;
    @Value("${get_speaker_meetings}")
    private String GET_SPEAKER_MEETUPS;
    @Value("${insert_new_meeting}")
    private String INSERT_NEW_MEETUP;
    @Value("${update_meetup}")
    private String UPDATE_MEETUP;
    @Value("${find_topic_id}")
    private String FIND_TOPIC_ID_BY_NAME;
    @Value("${add_topic_to_meeting}")
    private String ADD_TOPIC_TO_MEETUP;

    @Override
    public List<Meetup> getAllMeetups() {
        return this.template.query(GET_ALL_MEETUPS, new MeetupMapper());
    }

    @Override
    public void insertNewMeetup(Meetup meetup) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id_speaker", meetup.getSpeakerId())
            .addValue("id_language", meetup.getLanguageId())
            .addValue("title", meetup.getTitle())
            .addValue("start_time", meetup.getDate())
            .addValue("min_atendees", meetup.getMinAttendees())
            .addValue("max_atendees", meetup.getMaxAttendees())
            .addValue("description", meetup.getDescription());
        template.update(INSERT_NEW_MEETUP, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            meetup.setId(holder.getKey().intValue());
            //adding topics to DB
            //TODO rewrite
            for (Topic topic : meetup.getTopics()) {
                addTopicToMeetup(meetup, topic);
            }
        }
    }

    @Override
    public void updateMeetup(Meetup meetup){
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id",meetup.getId())
            .addValue("id_speaker", meetup.getSpeakerId())
            .addValue("id_language", meetup.getLanguageId())
            .addValue("title", meetup.getTitle())
            .addValue("start_time", meetup.getDate())
            .addValue("min_atendees", meetup.getMinAttendees())
            .addValue("max_atendees", meetup.getMaxAttendees())
            .addValue("description", meetup.getDescription());
        template.update(UPDATE_MEETUP, param, holder);
        if (holder.getKeys() != null) {
            meetup.setId(holder.getKey().intValue());
            //adding topics to DB
            //TODO rewrite
            for (Topic topic : meetup.getTopics()) {
                addTopicToMeetup(meetup, topic);
            }
        }
    }

    @Override
    public void addTopicToMeetup(Meetup meetup, Topic topic) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("name",
            topic.getName());
        Integer topic_id = template
            .queryForObject(FIND_TOPIC_ID_BY_NAME, namedParameters,
                Integer.class);

        Map parametersForAddingTopic = new HashMap();
        parametersForAddingTopic.put("id_meetup", meetup.getId());
        parametersForAddingTopic.put("id_topic", topic_id);
        template.update(ADD_TOPIC_TO_MEETUP, parametersForAddingTopic);
    }

    @Override
    public List<Meetup> getSpeakerMeetups(int speakerID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id_speaker", speakerID);
        return this.template
            .query(GET_SPEAKER_MEETUPS, param, new MeetupMapper());
    }
}
