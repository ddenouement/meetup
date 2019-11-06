package com.meetup.repository.impl;

import com.meetup.entities.Feedback;
import com.meetup.entities.Meetup;
import com.meetup.utils.MeetupState;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.model.mapper.MeetupMapper;
import com.meetup.model.mapper.TopicMapper;
import com.meetup.model.mapper.UserMapper;
import com.meetup.repository.IMeetupDAO;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meetup.utils.DbQueryConstants;
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
 * . Meetup repository class. Used to communicate with database, to perform
 * operations with Meetups.
 */
@Repository
@PropertySource("classpath:sql/meetup_queries.properties")
public class MeetupDaoImpl implements IMeetupDAO {

    /**
     * . JDBC template.
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * . SQL reference script. Retrieve all meetups.
     */
    @Value("${get_all_meetings}")
    private String getAllMeetups;
    /**
     * . SQL reference script. Retrieve all meetups.
     */
    @Value("${get_all_meetups_by_pages}")
    private String getAllMeetupsByPages;

    /**
     * SQL reference script. Retrieve all meetups with specified start time.
     */
    @Value("${get_meetups_by_start_time}")
    private String getMeetupsByStartTime;
    /**
     * . SQL reference script. Retrieve specific speaker meetups.
     */
    @Value("${get_speaker_meetings}")
    private String getSpeakerMeetups;
    /**
     * . SQL reference script. Retrieve specific speaker meetups from past.
     */
    @Value("${past_hosted_meetings_of_user}")
    private String getHostedMeetupsPast;
    /**
     * . SQL reference script. Retrieve specific speaker meetups from future.
     */
    @Value("${future_hosted_meetings_of_user}")
    private String getHostedMeetupsFuture;
    /**
     * . SQL reference script. Retrieve specific joined  meetups from past.
     */
    @Value("${past_joined_meetups_of_user}")
    private String getJoinedMeetupsPast;
    /**
     * . SQL reference script. Retrieve specific joined meetups from future.
     */
    @Value("${future_joined_meetups_of_user}")
    private String getJoinedMeetupsFuture;
    /**
     * SQL reference script. Add meetup to DB.
     */
    @Value("${insert_new_meetup}")
    private String insertNewMeetup;
    /**
     * SQL reference script. Update existing meetup in DB.
     */
    @Value("${update_meetup}")
    private String updateMeetup;
    /**
     * . SQL reference script. Retrieve topic.ts by name.
     */
    @Value("${find_topic_id}")
    private String findTopicIdByName;
    /**
     * . SQL reference script. Add topic.ts to specific meetup.
     */
    @Value("${add_topic_to_meeting}")
    private String addTopicToMeetup;
    /**
     * . SQL reference script. Add topic.ts to specific meetup.
     */
    @Value("${get_meetup_topics}")
    private String findMeetupTopics;
    /**
     * . SQL reference script. Get users joined to meetup.
     */
    @Value("${get_joined_meetups_of_user}")
    private String getUsersJoinedMeetups;
    /**
     * . SQL reference script. Add user to meetup.
     */
    @Value("${add_user_to_meetup}")
    private String addUserToMeetup;
    /**
     * . SQL reference script. Remove user from meetup.
     */
    @Value("${remove_user_from_meetup}")
    private String removeUserFromMeetup;
    /**
     * . SQL reference script. Remove all users from meetup.
     */
    @Value("${remove_all_users_from_meetup}")
    private String removeAllUsersFromMeetup;
    /**
     * . SQL reference script. Get all users on specific meetup.
     */
    @Value("${get_users_on_meetup}")
    private String getUsersOnMeetup;
    /**
     * . SQL reference script. Get meetup by ID.
     */
    @Value("${find_meetup_by_id}")
    private String getMeetupByID;
    /**
     * SQL reference script. Rate meetup.
     */
    @Value("${rate_meetup}")
    private String rateMeetup;
    /**
     * SQL reference script. Remove meetup topics.
     */
    @Value("${remove_meetup_topics}")
    private String removeMeetupTopics;

    /**
     * SQL reference script. Insert meetup topic.
     */
    @Value("${insert_meetup_topic}")
    private String insertMeetupTopic;

    /**
     * . Get all meetups from DB.
     *
     * @return List of all meetups.
     */
    @Override
    public List<Meetup> getAllMeetups() {
        List<Meetup> meetups = this.template.query(
            getAllMeetups, new MeetupMapper());
        for (Meetup m : meetups) {
            m.setTopics(getMeetupTopics(m.getId()));
        }
        return meetups;
    }

    @Override
    public List<Meetup> getAllMeetupsByPages(final Integer offset, final Integer limit) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue(DbQueryConstants.offset.name(), offset)
                .addValue(DbQueryConstants.limit.name(), limit);
        List<Meetup> meetups = this.template.query(
                getAllMeetupsByPages,param, new MeetupMapper());
        for (Meetup m : meetups) {
            m.setTopics(getMeetupTopics(m.getId()));
        }
        return meetups;
    }

    /**
     * Retrieve all meetups from database that start at the specified time.
     *
     * @param startTime start of meetup
     * @return List of meetups
     */
    @Override
    public List<Meetup> getMeetupsByStartTime(final LocalDateTime startTime) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.start_time.name(), startTime);
        List<Meetup> meetups = this.template.query(
            getMeetupsByStartTime, param, new MeetupMapper());
        for (Meetup m : meetups) {
            m.setTopics(getMeetupTopics(m.getId()));
        }
        return meetups;
    }

    /**
     * Insert new meetup in DB.
     *
     * @param meetup Meetup to be added.
     * @return added Meetup
     */
    @Override
    public Meetup insertNewMeetup(final Meetup meetup) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_speaker.name(), meetup.getSpeakerId())
            .addValue(DbQueryConstants.id_language.name(),
                meetup.getLanguageId())
            .addValue(DbQueryConstants.id_state.name(),
                MeetupState.SCHEDULED.getCode())
            .addValue(DbQueryConstants.title.name(), meetup.getTitle())
            .addValue(DbQueryConstants.start_time.name(), meetup.getStartDate())
            .addValue(DbQueryConstants.duration_minutes.name(),
                meetup.getDurationMinutes())
            .addValue(DbQueryConstants.min_attendees.name(),
                meetup.getMinAttendees())
            .addValue(DbQueryConstants.max_attendees.name(),
                meetup.getMaxAttendees())
            .addValue(DbQueryConstants.description.name(),
                meetup.getDescription());
        template.update(insertNewMeetup, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            meetup.setId(holder.getKey().intValue());
            //adding topics to DB
            //TODOo rewrite
            for (Topic topic : meetup.getTopics()) {
                addTopicToMeetup(meetup, topic);
            }
        }
        return meetup;
    }

    /**
     * . Update existing meetup in DB.
     *
     * @param editedMeetup Edited meetup.
     * @param meetupID Meetup to be updated.
     * @return edited meetup
     */
    @Override
    public Meetup updateMeetup(final Meetup editedMeetup, final int meetupID) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), meetupID)
            .addValue(DbQueryConstants.id_speaker.name(),
                editedMeetup.getSpeakerId())
            .addValue(DbQueryConstants.id_language.name(),
                editedMeetup.getLanguageId())
            .addValue(DbQueryConstants.id_state.name(),
                MeetupState.SCHEDULED.getCode())
            .addValue(DbQueryConstants.title.name(), editedMeetup.getTitle())
            .addValue(DbQueryConstants.start_time.name(),
                editedMeetup.getStartDate())
            .addValue(DbQueryConstants.duration_minutes.name(),
                editedMeetup.getDurationMinutes())
            .addValue(DbQueryConstants.min_attendees.name(),
                editedMeetup.getMinAttendees())
            .addValue(DbQueryConstants.max_attendees.name(),
                editedMeetup.getMaxAttendees())
            .addValue(DbQueryConstants.description.name(),
                editedMeetup.getDescription());
        template.update(updateMeetup, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            editedMeetup.setId(holder.getKey().intValue());
            removeMeetupTopics(editedMeetup.getId());
            for (Topic topic : editedMeetup.getTopics()) {
                addTopicToMeetup(editedMeetup, topic);
            }
        }
        return editedMeetup;
    }

    /**
     * Get meetup from DB by ID.
     *
     * @param meetupID Meetup id.
     * @return Meetup object.
     */
    @Override
    public Meetup findMeetupByID(final int meetupID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), meetupID);
        return this.template
            .queryForObject(getMeetupByID, param, new MeetupMapper());
    }

    /**
     * . Add topic.ts to meetup in DB.
     *
     * @param meetup Meetup object, that should have topic.ts
     * @param topic Topic to be added to meetup.
     */
    @Override
    public void addTopicToMeetup(final Meetup meetup, final Topic topic) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(
            DbQueryConstants.name.name(),
            topic.getName());
        Integer topicId = template
            .queryForObject(findTopicIdByName, namedParameters,
                Integer.class);

        Map parametersForAddingTopic = new HashMap();
        parametersForAddingTopic.put(
            DbQueryConstants.id_meetup.name(), meetup.getId());
        parametersForAddingTopic.put(
            DbQueryConstants.id_topic.name(), topicId);
        template.update(addTopicToMeetup, parametersForAddingTopic);
    }

    /**
     * Get topics of meetup.
     *
     * @param meetupID Meetup ID.
     * @return List of topics.
     */
    @Override
    public List<Topic> getMeetupTopics(final int meetupID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_meetup.name(), meetupID);
        return template.query(findMeetupTopics, param, new TopicMapper());
    }

    /**
     * . Get all meetups of specific speaker.
     *
     * @param speakerID Speaker ID
     * @return List of meetups of specific speaker.
     */
    @Override
    public List<Meetup> getSpeakerMeetupsPast(final int speakerID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_speaker.name(), speakerID);
        return this.template
            .query(getHostedMeetupsPast, param, new MeetupMapper());
    }

    /**
     * . Get all meetups of specific speaker (future).
     *
     * @param speakerID Speaker ID
     * @return List of meetups of specific speaker.
     */
    @Override
    public List<Meetup> getSpeakerMeetupsFuture(final int speakerID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_speaker.name(), speakerID);
        return this.template
            .query(getHostedMeetupsFuture, param, new MeetupMapper());
    }

    @Override
    public List<Meetup> getSpeakerMeetupsAllHosted(final int speakerID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_speaker.name(), speakerID);
        return this.template
            .query(getSpeakerMeetups, param, new MeetupMapper());
    }

    /**
     * Rate specific meetup.
     *
     * @param meetupID Meetup ID.
     * @param userID User ID.
     * @param feedback Feedback.
     */
    @Override
    public void rateMeetup(final int meetupID, final int userID,
        final Feedback feedback) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id_meetup", meetupID)
            .addValue("id_user", userID)
            .addValue("speaker_rate", feedback.getRate())
            .addValue("speaker_feedback", feedback.getFeedback())
            .addValue("time_posted", getCurrentTimestamp());
        template.update(rateMeetup, param);
    }

    /**
     * Remove meetup topics.
     *
     * @param meetupID Meetup ID.
     */
    @Override
    public void removeMeetupTopics(final int meetupID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_meetup.name(), meetupID);
        template.update(removeMeetupTopics, param);
    }

    /**
     * Add topic to meetup.
     *
     * @param meetupID Meetup ID.
     * @param topicID Topic ID.
     */
    @Override
    public void addMeetupTopic(final int meetupID, final int topicID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_meetup.name(), meetupID)
            .addValue(DbQueryConstants.id_topic.name(), topicID);
        template.update(insertMeetupTopic, param);
    }

    /**
     * . Get all meetups, that user will attend.
     *
     * @param userID User ID
     * @return List of meetups.
     */
    @Override
    public List<Meetup> getUsersJoinedMeetupsFuture(final int userID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_user.name(), userID);
        return this.template
            .query(getJoinedMeetupsFuture, param, new MeetupMapper());
    }

    /**
     * . Get all meetups, that user have joined and attended.
     *
     * @param userID User ID
     * @return List of meetups.
     */
    @Override
    public List<Meetup> getUsersJoinedMeetupsPast(final int userID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_user.name(), userID);
        return this.template
            .query(getJoinedMeetupsPast, param, new MeetupMapper());
    }

    /**
     * . Add user to specific meetup.
     *
     * @param meetupID Meetup, that user should register to.
     * @param userID User that takes part in meetup.
     */
    @Override
    public void addUserToMeetup(final int meetupID, final int userID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_meetup.name(), meetupID)
            .addValue(DbQueryConstants.id_user.name(), userID);
        template.update(addUserToMeetup, param);
    }

    /**
     * . Remove user from specific meetup.
     *
     * @param meetupID Meetup, that user should leave to.
     * @param userID User that leaves meetup.
     */
    @Override
    public void removeUserFromMeetup(final int meetupID, final int userID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_meetup.name(), meetupID)
            .addValue(DbQueryConstants.id_user.name(), userID);
        template.update(removeUserFromMeetup, param);
    }

    /**
     * Remove all users from meetup.
     *
     * @param meetupID Meetup, that user should leave to.
     */
    @Override
    public void removeAllUsersFromMeetup(final int meetupID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_meetup.name(), meetupID);
        template.update(removeAllUsersFromMeetup, param);
    }

    /**
     * Get users registered on meetup.
     *
     * @param meetupId Meetup ID
     * @return List of users.
     */
    @Override
    public List<User> getUsersOnMeetup(final int meetupId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.meetup_id.name(), meetupId);
        return this.template.query(getUsersOnMeetup, param, new UserMapper());
    }

    /**
     * Get current date.
     *
     * @return SQL Timestamp date format.
     */
    public Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }
}
