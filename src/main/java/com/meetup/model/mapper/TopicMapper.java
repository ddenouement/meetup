package com.meetup.model.mapper;

import com.meetup.entities.Topic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class TopicMapper implements RowMapper<Topic> {
    public final Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
        Topic topic = new Topic();
        topic.setId(rs.getInt("id"));
        topic.setName(rs.getString("name"));
        return topic;
    }
}