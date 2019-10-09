package com.meetup.service.impl;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.repository.impl.MeetingDaoImpl;
import com.meetup.repository.impl.TopicDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.meetup.service.RoleProcessor.isSpeaker;

/**
 * @author Dmytro Zubko
 */

@Component
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    TopicDaoImpl topicDao;
    @Autowired
    MeetingDaoImpl meetingDao;
    @Autowired
    UserDaoImpl userDao;

    /**
     * Method used to get topics list from database, using topic repository (TopicDao)
     *
     * @return List of "Topic" objects
     */
    @Override
    public List<Topic> getAllTopics(String login) {
        if (userDao.findUserByLogin(login) == null) {
            return null;
        } else {
            return topicDao.getAllTopics();
        }
    }

    /**
     * Method used to get meetings list from database, using meeting repository (MeetingDao)
     *
     * @return List of "Meeting" objects
     */
    @Override
    public List<Meeting> getAllMeetings(String login) {
        if (userDao.findUserByLogin(login) == null) {
            return null;
        } else {
            return meetingDao.getAllMeetings();
        }
    }

    /**
     * @param meeting Object, to be added to database
     * @param login   User (Speaker) login that creates a meeting
     * @return Meeting that was just created
     */
    @Override
    public Meeting createMeeting(Meeting meeting, String login) {
        if (isSpeaker(userDao.findUserByLogin(login))) {
            meetingDao.insertNewMeeting(meeting);
            return meeting;
        } else {
            return null;
        }

    }

    /**
     * Method used to get specific speaker meetings list from database, using meeting repository (MeetingDao)
     *
     * @return List of "Meeting" objects
     */
    @Override
    public List<Meeting> getSpeakerMeetings(String login) {
        User user = userDao.findUserByLogin(login);
        if (isSpeaker(user)) {
            return meetingDao.getSpeakerMeetings(user.getId());
        } else {
            return null;
        }
    }
}
