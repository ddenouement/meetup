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

/**
 * Meeting service (implementation). Used to manage user functionality.
 */
@Component
public class MeetingServiceImpl implements MeetingService {

    /**
     * Topic repository.
     */
    private TopicDaoImpl topicDao;
    /**
     * Meeting repository.
     */
    private MeetingDaoImpl meetingDao;
    /**
     * User repository.
     */
    private UserDaoImpl userDao;
    /**
     * Login validation service.
     */
    private LoginValidatorServiceImpl loginValidatorService;

    /**
     * Meeting service constructor.
     *
     * @param topicDao Topic repository
     * @param meetingDao Meeting repository
     * @param userDao User repository
     * @param loginValidatorService Login validation service
     */
    MeetingServiceImpl(@Autowired final TopicDaoImpl topicDao,
        @Autowired final MeetingDaoImpl meetingDao,
        @Autowired final UserDaoImpl userDao,
        @Autowired final LoginValidatorServiceImpl loginValidatorService) {
        this.topicDao = topicDao;
        this.meetingDao = meetingDao;
        this.userDao = userDao;
        this.loginValidatorService = loginValidatorService;
    }

    /**
     * Method used to get topics list from database, using topic repository
     * (TopicDao).
     *
     * @return List of "Topic" objects
     */
    @Override
    public List<Topic> getAllTopics(final String token) {
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
     * (MeetingDao).
     *
     * @return List of "Meeting" objects
     */
    @Override
    public List<Meeting> getAllMeetings(final String token) {
        String userLogin = loginValidatorService.extractLogin(token);
        if (userDao.findUserByLogin(userLogin) == null) {
            throw new NoSuchElementException();
        } else {
            return meetingDao.getAllMeetings();
        }
    }

    /**
     * @param meeting Object, to be added to database.
     * @param token Token with user login that creates a meeting
     * @return Meeting that was just created
     */
    @Override
    public Meeting createMeeting(final Meeting meeting, final String token)
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
     * meeting repository (MeetingDao).
     *
     * @return List of "Meeting" objects
     */
    @Override
    public List<Meeting> getSpeakerMeetings(final String token)
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
