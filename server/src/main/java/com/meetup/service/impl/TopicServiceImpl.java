package com.meetup.service.impl;

import com.meetup.entities.Topic;
import com.meetup.error.TopicIsUsedException;
import com.meetup.error.TopicNotFoundException;
import com.meetup.repository.ITopicDAO;
import com.meetup.repository.impl.TopicDaoImpl;
import com.meetup.service.ITopicService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Topic service (implementation). Used to manage topics.
 */
@Service
public class TopicServiceImpl implements ITopicService {

    /**
     * Topic repository.
     */
    private ITopicDAO topicDao;

    /**
     * Topic service constructor.
     *
     * @param topicDao Topic repository
     */
    TopicServiceImpl(@Autowired final TopicDaoImpl topicDao) {
        this.topicDao = topicDao;
    }

    /**
     * Method used to get topics list from database, using topic.ts repository
     * (TopicDao).
     *
     * @return List of "Topic" objects
     */
    @Override
    public List<Topic> getAllTopics() {
        List<Topic> allTopics = topicDao.getAllTopics();
        if (allTopics.isEmpty()) {
            throw new TopicNotFoundException();
        }
        return allTopics;
    }

    /**
     * Get topic by ID.
     *
     * @param topicID Topic ID.
     * @return Topic.
     */
    @Override
    public Topic getTopic(final int topicID) {
        return topicDao.findTopicByID(topicID);
    }

    /**
     * Add topic.
     *
     * @param topic Created topic.
     * @return Created topic.
     */
    @Override
    public Topic createTopic(final Topic topic) {
        Topic created;
        if (topicDao.findTopicByName(topic.getName()) != null) {
            throw new TopicIsUsedException();
        } else {
            created = topicDao.insertTopic(topic);
        }
        return created;
    }

    /**
     * Update topic.
     *
     * @param topicID Topic ID to be updated.
     * @param topic Updated topic.
     * @return Updated topic.
     */
    @Override
    public Topic updateTopic(final int topicID, final Topic topic) {
        Topic edited = topicDao.updateTopic(topicID, topic);
        edited.setId(topicID);
        return edited;
    }

    /**
     * Remove topic by ID.
     *
     * @param topicID Topic ID.
     */
    @Override
    public void removeTopic(final int topicID) {
        topicDao.removeTopic(topicID);
    }
}
