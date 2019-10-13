package com.meetup.service.impl;

import static com.meetup.service.RoleProcessor.isSpeaker;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.repository.impl.MeetupDaoImpl;
import com.meetup.repository.impl.TopicDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.MeetupService;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Meetup service (implementation). Used to manage meetup functionality.
 */
@Component
public class MeetupServiceImpl implements MeetupService {

    /**
     * Topic repository.
     */
    private TopicDaoImpl topicDao;
    /**
     * Meetup repository.
     */
    private MeetupDaoImpl meetupDao;
    /**
     * User repository.
     */
    private UserDaoImpl userDao;
    /**
     * Login validation service.
     */
    private LoginValidatorServiceImpl loginValidatorService;

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
        if (allTopics.isEmpty()){
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
        if (allMeetups.isEmpty()){
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
}
