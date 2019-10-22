package com.meetup.service.impl;

import static com.meetup.service.RoleProcessor.isSpeaker;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.error.MeetupNotFoundException;
import com.meetup.error.OutOfSlotsException;
import com.meetup.error.SpeakerOperationNotAllowedException;
import com.meetup.error.TopicNotFoundException;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.ITopicDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.MeetupDaoImpl;
import com.meetup.repository.impl.TopicDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.IMeetupService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.meetup.entities.Pair;
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
     * Meetup service constructor.
     *
     * <<<<<<< HEAD
     *
     * @param topicDao Topic repository
     * @param meetupDao Meetup repository
     * @param userDao User repository
     */
    MeetupServiceImpl(@Autowired final TopicDaoImpl topicDao,
        @Autowired final MeetupDaoImpl meetupDao,
        @Autowired final UserDaoImpl userDao) {
        this.topicDao = topicDao;
        this.meetupDao = meetupDao;
        this.userDao = userDao;
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
            throw new MeetupNotFoundException();
        }
        return allMeetups;

    }

    /**
     * @param meetup Object, to be added to database.
     * @param userLogin Login of user that creates a meetup
     */
    @Override
    public void createMeetup(final Meetup meetup, final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        if (isSpeaker(user)) {
            meetup.setSpeakerId(user.getId());
            meetupDao.insertNewMeetup(meetup);
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

    /**
     * @param editedMeetup Object, to be added to database.
     * @param userLogin Login of user that creates a meetup
     */
    @Override
    public void updateMeetup(final int meetupID,
        final Meetup editedMeetup,
        final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        Meetup existingMeetup = meetupDao.findMeetupByID(meetupID);
        if (isSpeaker(user)) {
            editedMeetup.setSpeakerId(user.getId());
            if (editedMeetup.getStateId() == 0) {
                editedMeetup.setStateId(existingMeetup.getStateId());
            }
            meetupDao.updateMeetup(editedMeetup, meetupID);
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

    /**
     * Cancel existing meetup.
     * @param meetupID Meetup ID to be canceled
     * @param login User login (that removes the meetup)
     */
    @Override
    public void cancelMeetup(final int meetupID, final String login) {
        User user = userDao.findUserByLogin(login);
        Meetup existingMeetup = meetupDao.findMeetupByID(meetupID);
        if (isSpeaker(user)) {
            existingMeetup.setStateId(MeetupStateConstants.CANCELED);
            meetupDao.updateMeetup(existingMeetup, meetupID);
            meetupDao.removeAllUsersFromMeetup(meetupID);
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

    /**
     * @param meetupID ID of meetup, that should be used to find meetup.
     */
    @Override
    public Meetup getMeetup(final int meetupID) {
        return meetupDao.findMeetupByID(meetupID);
    }

    /**
     * Method used to get specific speaker meetings list from database, using
     * meeting repository (MeetupDao).
     *
     * @return List of "Meetup" objects
     */
    @Override
    public List<Meetup> getSpeakerMeetups(final int speakerID) {
        return meetupDao.getSpeakerMeetups(speakerID);
    }

    /**
     * . Pair<past; future> of meetups hosted by speaker
     *
     * @param login login of user
     * @return pair of lists of user hosted meetus (null if not a speaker)
     */
    @Override
    public Pair<List<Meetup>, List<Meetup>> getSpeakerMeetupsByLogin(
        final String login) {
        User user = userDao.findUserByLogin(login);
        if (isSpeaker(user)) {
            List<Meetup> allTogetherMeetups =
                meetupDao.getSpeakerMeetups(user.getId());
            List<Meetup> past = new ArrayList<>();
            List<Meetup> future = new ArrayList<>();
            for (Meetup a : allTogetherMeetups) {
                if (a.getStartDate().isBefore(LocalDateTime.now())) {
                    past.add(a);
                } else {
                    future.add(a);
                }
            }
            return new Pair<>(past, future);
        } else {
            return new Pair<>(new ArrayList<>(), new ArrayList<>());
        }
    }


    /**
     * Register user for specified meetup.
     *
     * @param meetupID Meetup, that will be used to register user to
     * @param userLogin User login
     */
    @Override
    public void joinMeetup(final int meetupID, final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        List<User> usersOnMeetup = meetupDao.getUsersOnMeetup(meetupID);
        Meetup currentMeetup = meetupDao.findMeetupByID(meetupID);
        if (currentMeetup.getStateId() == MeetupStateConstants.SCHEDULED) {
            if (currentMeetup.getMaxAttendees() == usersOnMeetup.size() + 1) {
                currentMeetup.setStateId(MeetupStateConstants.BOOKED);
            }
            meetupDao.addUserToMeetup(currentMeetup.getId(), user.getId());
            meetupDao.updateMeetup(currentMeetup, currentMeetup.getId());
        } else {
            throw new OutOfSlotsException();
        }
    }

    /**
     * Remove user from specified meetup.
     *
     * @param meetupID Meetup, that will be used to remove user to
     * @param userLogin User login
     */
    @Override
    public void leaveMeetup(final int meetupID, final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        Meetup currentMeetup = meetupDao.findMeetupByID(meetupID);
        meetupDao.removeUserFromMeetup(meetupID, user.getId());
        currentMeetup.setStateId(MeetupStateConstants.SCHEDULED);
        meetupDao.updateMeetup(currentMeetup, currentMeetup.getId());
    }

    /**
     * . Meetups that user joined
     *
     * @param id id
     * @return List of meetups
     */
    @Override
    public Pair<List<Meetup>, List<Meetup>> getUserJoinedMeetups(final int id) {
        List<Meetup> allTogetherMeetups = meetupDao.getUsersJoinedMeetups(id);
        List<Meetup> past = new ArrayList<>();
        List<Meetup> future = new ArrayList<>();
        for (Meetup a : allTogetherMeetups) {
            if (a.getStartDate().isBefore(LocalDateTime.now())) {
                past.add(a);
            } else {
                future.add(a);
            }
        }
        return new Pair<>(past, future);
    }
}
