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

    /**
     * Find topic by ID.
     * @param id
     * ID of topic.
     * @return
     * Topic.
     */
    Topic findTopicByID(int id);

    /**
     * Find topic by ID.
     * @param name
     * Name of topic.
     * @return
     * Topic.
     */
    Topic findTopicByName(String name);

    /**
     * Insert new topic to DB.
     * @param topic
     * Topic to be added to DB.
     * @return Created topic.
     */
    Topic insertTopic(Topic topic);

    /**
     * Remove topic from DB.
     * @param topicID
     * Topic ID to be removed.
     */
    void removeTopic(int topicID);

    /**
     * Update topic.
     * @param topic
     * Updated topic.
     * @param topicID Topic ID to be updated.
     * @return
     * Updated topic.
     */
    Topic updateTopic(int topicID, Topic topic);
}
