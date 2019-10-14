package com.meetup.service.impl;

import static com.meetup.service.RoleProcessor.isSpeaker;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.ITopicDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.MeetupDaoImpl;
import com.meetup.repository.impl.TopicDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.IMeetupService;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Meetup service (implementation). Used to manage meetup functionality.
 */
@Component
public class MeetupServiceImpl implements IMeetupService {

    /**
     * Topic repository.
     */
    private ITopicDAO topicDao;
    /**
     * Meetup repository.
     */
    private IMeetupDAO meetupDao;
    /**
     * User repository.
     */
    private IUserDAO userDao;
    /**
     * Login validation service.
     */
    private ILoginValidatorService loginValidatorService;

    /**
     * Meetup service constructor.
     *
     * @param topicDao Topic repository
     * @param meetupDao Meetup repository
     * @param userDao User repository
     * @param loginValidatorService Login validation service
     */
    MeetupServiceImpl(@Autowired final TopicDaoImpl topicDao,
        @Autowired final MeetupDaoImpl meetupDao,
        @Autowired final UserDaoImpl userDao,
        @Autowired final LoginValidatorServiceImpl loginValidatorService) {
        this.topicDao = topicDao;
        this.meetupDao = meetupDao;
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
    public List<Topic> getAllTopics() {
        List<Topic> allTopics = topicDao.getAllTopics();
        if (allTopics.isEmpty()) {
            throw new NoSuchElementException();
        }
        return allTopics;
    }

    //TODO pagination

    /**
     * Method used to get meetups list from database, using meetup repository
     * (MeetupDao).
     *
     * @return List of "Meetup" objects
     */
    @Override
    public List<Meetup> getAllMeetups() {
        List<Meetup> allMeetups = meetupDao.getAllMeetups();
        if (allMeetups.isEmpty()) {
            throw new NoSuchElementException();
        }
        return allMeetups;

    }

    /**
     * @param meetup Object, to be added to database.
     * @param token Token with user login that creates a meetup
     * @return Meetup that was just created
     */
    @Override
    public Meetup createMeetup(final Meetup meetup, final String token)
        throws IllegalAccessException {
        String userLogin = loginValidatorService.extractLogin(token);
        User user = userDao.findUserByLogin(userLogin);
        if (isSpeaker(user)) {
            meetup.setSpeakerId(user.getId());
            meetupDao.insertNewMeetup(meetup);
            return meetup;
        } else {
            throw new IllegalAccessException();
        }
    }

    /**
     * @param meetup Object, to be added to database.
     * @param token Token with user login that creates a meetup
     * @return Meetup that was just created
     */
    @Override
    public Meetup updateMeetup(final Meetup meetup, final String token)
        throws IllegalAccessException {
        String userLogin = loginValidatorService.extractLogin(token);
        User user = userDao.findUserByLogin(userLogin);
        if (isSpeaker(user)) {
            meetup.setSpeakerId(user.getId());
            meetupDao.updateMeetup(meetup);
            //TODO return new meetup
            return meetup;
        } else {
            throw new IllegalAccessException();
        }
    }

    /**
     * Method used to get specific speaker meetings list from database, using
     * meeting repository (MeetupDao).
     *
     * @return List of "Meetup" objects
     */
    @Override
    public List<Meetup> getSpeakerMeetups(final String token)
        throws IllegalAccessException {
        String userLogin = loginValidatorService.extractLogin(token);
        User user = userDao.findUserByLogin(userLogin);
        if (isSpeaker(user)) {
            return meetupDao.getSpeakerMeetups(user.getId());
        } else {
            throw new IllegalAccessException();
        }
    }

    /**
     * Register user for specified meetup.
     * @param meetup
     * Meetup, that will be used to register user to
     * @param token
     * JSON web token to extract user credentials
     * @return
     * Meetup
     */
    @Override
    public Meetup joinMeetup(final Meetup meetup, final String token) {

        return null;
    }
}
