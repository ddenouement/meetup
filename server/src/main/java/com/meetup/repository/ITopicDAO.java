package com.meetup.repository;

import com.meetup.entities.Topic;
import java.util.List;

/**
 * Interface for Topic repository (DAO).
 */
public interface ITopicDAO {

    /**
     * Get all topics from DB.
     * @return
     * List of all topics.
     */
    List<Topic> getAllTopics();
}