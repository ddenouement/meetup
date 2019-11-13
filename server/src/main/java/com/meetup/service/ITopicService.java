package com.meetup.service;

import com.meetup.entities.Topic;
import java.util.List;

/**
 * Topic service interface. Used to manage topics.
 */
public interface ITopicService {

    /**
     * Retrieve all available topics.
     *
     * @return List of all topics
     */
    List<Topic> getAllTopics();

    /**
     * Get topic by ID.
     * @param topicID
     * Topic ID.
     * @return
     * Topic.
     */
    Topic getTopic(int topicID);

    /**
     * Add topic.
     * @param topic
     * Created topic.
     * @return
     * Created topic.
     */
    Topic createTopic(Topic topic);

    /**
     * Update topic.
     * @param topicID
     * Topic ID to be updated.
     * @param topic
     * Updated topic.
     * @return
     * Updated topic.
     */
    Topic updateTopic(int topicID, Topic topic);

    /**
     * Remove topic by ID.
     * @param topicID
     * Topic ID.
     */
    void removeTopic(int topicID);
}
