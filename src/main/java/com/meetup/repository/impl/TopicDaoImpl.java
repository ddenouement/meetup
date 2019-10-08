package com.meetup.repository.impl;

import com.meetup.entities.Topic;
import com.meetup.repository.ITopicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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



    private static final class TopicMapper implements RowMapper<Topic> {
        public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
            Topic topic = new Topic();
            topic.setId(rs.getInt("id"));
            topic.setName(rs.getString("name"));
            return topic;
        }
    }
}
