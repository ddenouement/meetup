package com.meetup.repository.impl;

import com.meetup.entities.Topic;
import com.meetup.model.mapper.TopicMapper;
import com.meetup.repository.ITopicDAO;
import com.meetup.utils.DbQueryConstants;
import java.util.List;
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
 * Topic repository class. Used to communicate with database, to perform
 * operations with Topic.
 */
@Repository
@PropertySource("classpath:sql/topic_queries.properties")
public class TopicDaoImpl implements ITopicDAO {

    /**
     * JDBC template.
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * SQL reference script. Retrieve all topics.
     */
    @Value("${get_all_topics}")
    private String getAllTopicsScript;

    /**
     * SQL reference script. Retrieve topic by ID.
     */
    @Value("${find_topic_by_id}")
    private String findTopicByID;

    /**
     * SQL reference script. Retrieve topic by name.
     */
    @Value("${find_topic_by_name}")
    private String findTopicByName;

    /**
     * SQL reference script. Create topic.
     */
    @Value("${create_topic}")
    private String insertTopic;

    /**
     * SQL reference script. Remove topic.
     */
    @Value("${remove_topic}")
    private String removeTopic;

    /**
     * SQL reference script. Update topic.
     */
    @Value("${update_topic}")
    private String updateTopic;

    /**
     * Retrieve all available topics from database.
     *
     * @return List of topics
     */
    @Override
    public List<Topic> getAllTopics() {
        return this.template.query(getAllTopicsScript, new TopicMapper());
    }

    /**
     * Find topic by ID.
     *
     * @param topicID ID of topic.
     * @return Topic.
     */
    @Override
    public Topic findTopicByID(final int topicID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), topicID);
        return this.template
            .queryForObject(findTopicByID, param, new TopicMapper());
    }

    /**
     * Find topic by name.
     * @param name
     * Name of topic.
     * @return
     * Topic.
     */
    @Override
    public Topic findTopicByName(final String name) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.name.name(), name);
        return this.template
            .queryForObject(findTopicByName, param, new TopicMapper());
    }

    /**
     * Insert new topic to DB.
     *
     * @param topic Topic to be added.
     */
    @Override
    public Topic insertTopic(final Topic topic) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.name.name(), topic.getName());
        template.update(insertTopic, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            topic.setId(holder.getKey().intValue());
        }
        return topic;
    }

    /**
     * Remove topic from DB.
     * @param topicID
     * Topic ID to be removed.
     */
    @Override
    public void removeTopic(final int topicID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), topicID);
        template.update(removeTopic, param);
    }

    /**
     * Update topic.
     * @param topicID Topic ID to be updated.
     * @param updatedTopic
     * Updated topic.
     * @return
     * Updated topic.
     */
    @Override
    public Topic updateTopic(final int topicID, final Topic updatedTopic) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), topicID)
            .addValue(DbQueryConstants.name.name(), updatedTopic.getName());
        template.update(updateTopic, param);
        return updatedTopic;
    }


}
