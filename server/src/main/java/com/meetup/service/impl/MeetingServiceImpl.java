package com.meetup.service.impl;

import static com.meetup.service.RoleProcessor.isSpeaker;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.repository.impl.MeetingDaoImpl;
import com.meetup.repository.impl.TopicDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.MeetingService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    TopicDaoImpl topicDao;
    @Autowired
    MeetingDaoImpl meetingDao;
    @Autowired
    UserDaoImpl userDao;
    @Autowired
    LoginValidatorServiceImpl loginValidatorService;

    /**
     * Method used to get topics list from database, using topic repository
     * (TopicDao)
     *
     * @return List of "Topic" objects
     */
    @Override
    public List<Topic> getAllTopics(String token) {
        String userLogin = loginValidatorService.extractLogin(token);
        if (userDao.findUserByLogin(userLogin) == null) {
            throw new NoSuchElementException();
        } else {
            return topicDao.getAllTopics();
        }
    }

    //TODO pagination

    /**
     * Method used to get meetings list from database, using meeting repository
     * (MeetingDao)
     *
     * @return List of "Meeting" objects
     */
    @Override
    public List<Meeting> getAllMeetings(String token) {
        String userLogin = loginValidatorService.extractLogin(token);
        if (userDao.findUserByLogin(userLogin) == null) {
            throw new NoSuchElementException();
        } else {
            return meetingDao.getAllMeetings();
        }
    }

    /**
     * @param meeting Object, to be added to database
     * @param token Token with user login that creates a meeting
     * @return Meeting that was just created
     */
    @Override
    public Meeting createMeeting(Meeting meeting, String token)
        throws IllegalAccessException {
        String userLogin = loginValidatorService.extractLogin(token);
        if (isSpeaker(userDao.findUserByLogin(userLogin))) {
            meetingDao.insertNewMeeting(meeting);
            return meeting;
        } else {
            throw new IllegalAccessException();
        }

    }

    /**
     * Method used to get specific speaker meetings list from database, using
     * meeting repository (MeetingDao)
     *
     * @return List of "Meeting" objects
     */
    @Override
    public List<Meeting> getSpeakerMeetings(String token)
        throws IllegalAccessException {
        String userLogin = loginValidatorService.extractLogin(token);
        User user = userDao.findUserByLogin(userLogin);
        if (isSpeaker(user)) {
            return meetingDao.getSpeakerMeetings(user.getId());
        } else {
            throw new IllegalAccessException();
        }
    }
}
