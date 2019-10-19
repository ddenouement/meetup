package com.meetup.repository.impl;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.model.mapper.MeetupMapper;
import com.meetup.model.mapper.UserMapper;
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

/**.
 * Meetup repository class.
 * Used to communicate with database, to perform operations with Meetups.
 */
@Repository
@PropertySource("classpath:sql/meetup_queries.properties")
public class MeetupDaoImpl implements IMeetupDAO {

    /**.
     * JDBC template.
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**.
     * SQL reference script.
     * Retrieve all meetups.
     */
    @Value("${get_all_meetings}")
    private String getAllMeetups;
    /**.
     * SQL reference script.
     * Retrieve specific speaker meetups.
     */
    @Value("${get_speaker_meetings}")
    private String getSpeakerMeetups;
    /**
     * SQL reference script.
     * Add meetup to DB.
     */
    @Value("${insert_new_meetup}")
    private String insertNewMeetup;
    /**
     * SQL reference script.
     * Update existing meetup in DB.
     */
    @Value("${update_meetup}")
    private String updateMeetup;
    /**.
     * SQL reference script.
     * Retrieve topic.ts by name.
     */
    @Value("${find_topic_id}")
    private String findTopicIdByName;
    /**.
     * SQL reference script.
     * Add topic.ts to specific meetup.
     */
    @Value("${add_topic_to_meeting}")
    private String addTopicToMeetup;
    /**.
     * SQL reference script.
     * Get users joined to meetup.
     */
    @Value("${get_joined_meetups_of_user}")
    private String getUsersJoinedMeetups;
    /**.
     * SQL reference script.
     * Add user to meetup.
     */
    @Value("${add_user_to_meetup}")
    private String addUserToMeetup;
    /**.
     * SQL reference script.
     * Remove user from meetup.
     */
    @Value("${remove_user_from_meetup}")
    private String removeUserFromMeetup;
    /**.
     * SQL reference script.
     * Get all users on specific meetup.
     */
    @Value("${get_users_on_meetup}")
    private String getUsersOnMeetup;

    /**.
     * Get all meetups from DB.
     * @return
     * List of all meetups.
     */
    @Override
    public List<Meetup> getAllMeetups() {
        return this.template.query(getAllMeetups, new MeetupMapper());
    }

    /**.
     * Insert new meetup in DB.
     * @param meetup
     * Meetup to be added.
     */
    @Override
    public void insertNewMeetup(final Meetup meetup) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id_speaker", meetup.getSpeakerId())
            .addValue("id_language", meetup.getLanguageId())
            .addValue("title", meetup.getTitle())
            .addValue("start_time", meetup.getStartDate())
            .addValue("duration_minutes", meetup.getDurationMinutes())
            .addValue("min_attendees", meetup.getMinAttendees())
            .addValue("max_attendees", meetup.getMaxAttendees())
            .addValue("description", meetup.getDescription());
        template.update(insertNewMeetup, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            meetup.setId(holder.getKey().intValue());
            //adding topics to DB
            //TODOo rewrite
            for (Topic topic : meetup.getTopics()) {
                addTopicToMeetup(meetup, topic);
            }
        }
    }

    /**.
     * Update existing meetup in DB.
     * @param meetup
     * Meetup to be updated.
     */
    @Override
    public void updateMeetup(final Meetup meetup) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id", meetup.getId())
            .addValue("id_speaker", meetup.getSpeakerId())
            .addValue("id_language", meetup.getLanguageId())
            .addValue("title", meetup.getTitle())
            .addValue("start_time", meetup.getStartDate())
            .addValue("duration_minutes", meetup.getDurationMinutes())
            .addValue("min_attendees", meetup.getMinAttendees())
            .addValue("max_attendees", meetup.getMaxAttendees())
            .addValue("description", meetup.getDescription());
        template.update(updateMeetup, param, holder);
        if (holder.getKeys() != null) {
            meetup.setId(holder.getKey().intValue());
            //adding topics to DB
            //TODOo rewrite
            for (Topic topic : meetup.getTopics()) {
                addTopicToMeetup(meetup, topic);
            }
        }
    }

    /**.
     * Add topic.ts to meetup in DB.
     * @param meetup
     * Meetup object, that should have topic.ts
     * @param topic
     * Topic to be added to meetup.
     */
    @Override
    public void addTopicToMeetup(final Meetup meetup, final Topic topic) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("name",
            topic.getName());
        Integer topicId = template
            .queryForObject(findTopicIdByName, namedParameters,
                Integer.class);

        Map parametersForAddingTopic = new HashMap();
        parametersForAddingTopic.put("id_meetup", meetup.getId());
        parametersForAddingTopic.put("id_topic", topicId);
        template.update(addTopicToMeetup, parametersForAddingTopic);
    }

    /**.
     * Get all meetups of specific speaker.
     * @param speakerID
     * Speaker ID
     * @return
     * List of meetups of specific speaker.
     */
    @Override
    public List<Meetup> getSpeakerMeetups(final int speakerID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id_speaker", speakerID);
        return this.template
            .query(getSpeakerMeetups, param, new MeetupMapper());
    }

    /**.
     * Get all meetups, that user have joined.
     * @param userID
     * User ID
     * @return
     * List of meetups.
     */
    @Override
    public List<Meetup> getUsersJoinedMeetups(final int userID) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_user", userID);
        return this.template
                .query(getUsersJoinedMeetups, param, new MeetupMapper());
    }

    /**.
     * Add user to specific meetup.
     * @param meetup
     * Meetup, that user should register to.
     * @param user
     * User that takes part in meetup.
     */
    @Override
    public void addUserToMeetup(final Meetup meetup, final User user) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id_meetup", meetup.getId())
            .addValue("id_user", user.getId());
        template.update(addUserToMeetup, param);
    }

    /**.
     * Remove user from specific meetup.
     * @param meetup
     * Meetup, that user should leave to.
     * @param user
     * User that leaves meetup.
     */
    @Override
    public void removeUserFromMeetup(final Meetup meetup, final User user) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id_meetup", meetup.getId())
            .addValue("id_user", user.getId());
        template.update(removeUserFromMeetup, param);
    }

    /**.
     * Get users, registered on meetup.
     * @param meetupId
     * Meetup ID
     * @return
     * List of users.
     */
    @Override
    public List<User> getUsersOnMeetup(final int meetupId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("meetup_id", meetupId);
        return this.template.query(getUsersOnMeetup, param, new UserMapper());
    }
}
