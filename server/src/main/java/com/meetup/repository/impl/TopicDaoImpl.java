package com.meetup.repository.impl;

import com.meetup.entities.Topic;
import com.meetup.model.mapper.TopicMapper;
import com.meetup.repository.ITopicDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * Topic repository class.
 * Used to communicate with database, to perform operations with Topic.
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
     * SQL reference script.
     * Retrieve all topics.
     */
    @Value("${get_all_topics}")
    private String getAllTopicsScript;

    /**
     * SQL reference script.
     * Retrieve topic by ID.
     */
    @Value("${find_topic_by_id}")
    private String findTopicByID;

    /**
     * Retrieve all available topics from database.
     * @return
     * List of topics
     */
    @Override
    public List<Topic> getAllTopics() {
        return this.template.query(getAllTopicsScript, new TopicMapper());
    }

    /**
     * Find topic by ID.
     * @param topicID
     * ID of topic.
     * @return
     * Topic.
     */
    @Override
    public Topic findTopicByID(final int topicID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id", topicID);
        return this.template.queryForObject(findTopicByID, param, new TopicMapper());
    }


}
