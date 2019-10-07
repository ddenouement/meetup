package com.meetup.repository.impl;

import com.meetup.entities.Topic;
import com.meetup.repository.ITopicDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicDaoImpl implements ITopicDAO {
    @Override
    public List<Topic> getAllTopics() {
        //TODO implement
        return null;
    }
}
