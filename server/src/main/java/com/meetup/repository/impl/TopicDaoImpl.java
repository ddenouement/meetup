package com.meetup.repository.impl;

import com.meetup.entities.Topic;
import com.meetup.model.mapper.TopicMapper;
import com.meetup.repository.ITopicDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:sql/meetup_queries.properties")
public class TopicDaoImpl implements ITopicDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Value("${get_all_topics}")
    private String GET_ALL_TOPICS;

    @Override
    public List<Topic> getAllTopics() {
        return this.template.query(GET_ALL_TOPICS, new TopicMapper());
    }


}
