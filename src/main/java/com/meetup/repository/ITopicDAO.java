package com.meetup.repository;

import com.meetup.entities.Topic;

import java.util.List;

/**
 * @author Dmytro Zubko
 */

public interface ITopicDAO {
    List<Topic> getAllTopics();
}
