package com.meetup.repository;

import com.meetup.entities.Topic;
import java.util.List;

public interface ITopicDAO {

    List<Topic> getAllTopics();
}
