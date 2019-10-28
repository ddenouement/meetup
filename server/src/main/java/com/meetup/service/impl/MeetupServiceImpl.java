package com.meetup.service.impl;

import static com.meetup.utils.RoleProcessor.isSpeaker;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.error.IllegalMeetupStateException;
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
import com.meetup.utils.MeetupStateConstants;
import com.meetup.utils.Pair;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
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
     * Save meetup to database with specified speaker.
     *
     * @param meetup Object, to be added to database.
     * @param userLogin Login of user that creates a meetup
     * @return created Meetup
     */
    @Override
    public Meetup createMeetup(final Meetup meetup, final String userLogin) {
        //TODO find out, if duration and start date should be managed on backend
        User user = userDao.findUserByLogin(userLogin);
        if (isSpeaker(user)) {
            meetup.setSpeakerId(user.getId());
            meetup.setStateId(MeetupStateConstants.SCHEDULED);
            return meetupDao.insertNewMeetup(meetup);
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

    /**
     * @param editedMeetup Object, to be added to database.
     * @param userLogin Login of user that creates a meetup
     * @return updated meetup
     */
    @Override
    public Meetup updateMeetup(final int meetupID,
        final Meetup editedMeetup,
        final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        //TODO null meetup handling
        Meetup existingMeetup = meetupDao.findMeetupByID(meetupID);
        if (isSpeaker(user) && user.getId() == existingMeetup.getSpeakerId()) {
            editedMeetup.setSpeakerId(user.getId());
            if (editedMeetup.getStateId() == 0) {
                editedMeetup.setStateId(existingMeetup.getStateId());
            }
            return meetupDao.updateMeetup(editedMeetup, meetupID);
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

    /**
     * Cancel existing meetup.
     *  @param meetupID Meetup ID to be canceled
     * @param login User login (that removes the meetup)
     * @return cancelled meetup
     */
    @Override
    public Meetup cancelMeetup(final int meetupID, final String login) {
        User user = userDao.findUserByLogin(login);
        //TODO null meetup handling
        Meetup existingMeetup = meetupDao.findMeetupByID(meetupID);
        if (isSpeaker(user)) {
            existingMeetup.setStateId(MeetupStateConstants.CANCELED);
            Meetup updated = meetupDao.updateMeetup(existingMeetup, meetupID);
            meetupDao.removeAllUsersFromMeetup(meetupID);
            return updated;
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
     * Start meetup for fixed duration.
     *  @param meetupID Meetup ID.
     * @param userLogin User login.
     * @return started meetup
     */
    @Override
    public Meetup startMeetup(final int meetupID, final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        Meetup currentMeetup = meetupDao.findMeetupByID(meetupID);
        if (isSpeaker(user)) {
            currentMeetup.setStateId(MeetupStateConstants.IN_PROGRESS);
            Meetup updated = meetupDao.updateMeetup(currentMeetup, meetupID);
            runTimer(meetupID);
            return updated;
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

    /**
     * Terminate meetup.
     * @param meetupID Meetup ID.
     * @param userLogin User login.
     * @return terminated meetup
     */
    @Override
    public Meetup terminateMeetup(final int meetupID, final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        Meetup currentMeetup = meetupDao.findMeetupByID(meetupID);
        if (isSpeaker(user)) {
            if (currentMeetup.getStateId()
                == MeetupStateConstants.IN_PROGRESS) {
                currentMeetup.setStateId(MeetupStateConstants.TERMINATED);
                return meetupDao.updateMeetup(currentMeetup, meetupID);
            } else {
                throw new IllegalMeetupStateException();
            }
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

    /**
     * Set meetup status to Passed after given period of time.
     *
     * @param currentMeetupID Current meetup.
     */
    private void runTimer(final int currentMeetupID) {
        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                    Meetup currentMeetup = meetupDao
                        .findMeetupByID(currentMeetupID);
                    if (currentMeetup.getStateId()
                        == MeetupStateConstants.IN_PROGRESS) {
                        currentMeetup.setStateId(MeetupStateConstants.PASSED);
                        meetupDao
                            .updateMeetup(currentMeetup, currentMeetup.getId());
                    }
                }
            },
            TimeUnit.
                MINUTES.
                toMillis(meetupDao.findMeetupByID(currentMeetupID)
                    .getDurationMinutes())
        );
    }

    /**
     * Method used to get specific speaker meetings list from database, using
     * meeting repository (MeetupDao).
     *
     * @return List of "Meetup" objects
     */
    @Override
    public List<Meetup> getSpeakerMeetups(final int speakerID) {
        return meetupDao.getSpeakerMeetupsAllHosted(speakerID);
    }

    /**
     * . List of meetups hosted by speaker in past
     * @param id id of user
     * @return   list  of user hosted meetups
     */
    @Override
    public  List<Meetup>  getHostedMeetupsPast(final int id){
            return    meetupDao.getSpeakerMeetupsPast(id);
    }
    /**
     * . List of meetups that will be hosted by this speaker
     * @param id id of user
     * @return  list of user hosted meetups
     */
    @Override
    public  List<Meetup>  getHostedMeetupsFuture(final int id){
        return    meetupDao.getSpeakerMeetupsFuture(id);
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
     * . Meetups that user attended in past
     *
     * @param id id
     * @return List of meetups
     */
    @Override
    public List<Meetup>  getJoinedMeetupsPast(final int id) {
       return meetupDao.getUsersJoinedMeetupsPast(id);
    }
    /**
     * . Meetups that user will attend in  future
     *
     * @param id id
     * @return List of meetups
     */
    @Override
    public List<Meetup>  getJoinedMeetupsFuture(final int id) {
        return meetupDao.getUsersJoinedMeetupsFuture(id);
    }
}
