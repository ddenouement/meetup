package com.meetup.service.impl;

import static com.meetup.utils.RoleProcessor.isSpeaker;

import com.meetup.entities.Meetup;
import com.meetup.entities.User;
import com.meetup.entities.dto.MeetupDisplayDTO;
import com.meetup.error.IllegalMeetupStateException;
import com.meetup.error.MeetupNotFoundException;
import com.meetup.error.OutOfSlotsException;
import com.meetup.error.SpeakerOperationNotAllowedException;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.MeetupDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.IMeetupService;
import com.meetup.service.INotificationService;
import com.meetup.utils.MeetupDTOConverter;
import com.meetup.utils.MeetupState;
import com.meetup.utils.NotificationConstants;
import java.time.LocalDateTime;
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
     * Meetup repository.
     */
    private IMeetupDAO meetupDao;
    /**
     * User repository.
     */
    private IUserDAO userDao;
    /**
     * Meetup DTO converter.
     */
    private MeetupDTOConverter meetupDTOConverter;
    /**
     * Notification operations.
     */
    private INotificationService notificationService;

    /**
     * Meetup service constructor.
     *
     * @param meetupDao Meetup repository
     * @param userDao User repository
     * @param meetupDTOConverter MeetupDTO converter.
     * @param notificationService notification operations
     */
    MeetupServiceImpl(@Autowired final MeetupDaoImpl meetupDao,
        @Autowired final UserDaoImpl userDao,
        @Autowired final MeetupDTOConverter meetupDTOConverter,
        @Autowired final INotificationService notificationService) {
        this.meetupDao = meetupDao;
        this.userDao = userDao;
        this.meetupDTOConverter = meetupDTOConverter;
        this.notificationService = notificationService;
    }

    /**
     * Method used to get meetups list from database, using meetup repository
     * (MeetupDao).
     *
     * @return List of "Meetup" objects
     */
    @Override
    public List<MeetupDisplayDTO> getAllMeetups() {
        return meetupDao.getAllMeetups();
    }

    @Override
    public List<MeetupDisplayDTO> getMeetupsByPages(final Integer offset,
        final Integer limit) {
       return meetupDao.getAllMeetupsByPages(offset, limit);
    }

    /**
     * Retrieve all meetups from database that start at the specified time.
     *
     * @param startTime start of meetup
     * @return List of meetups
     */
    @Override
    public List<Meetup> getMeetupsByStartTime(final LocalDateTime startTime) {
        return meetupDao.getMeetupsByStartTime(startTime);
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
            meetup.setStateId(MeetupState.SCHEDULED.getCode());
            Meetup inserted = meetupDao.insertNewMeetup(meetup);
            notificationService.sendNewSubscribedMeetupNotifications(inserted);
            return inserted;
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

    /**
     * Update meetup.
     *
     * @param meetupID id of meetup to update
     * @param editedMeetup Object, to be added to database.
     * @param userLogin Login of user that creates a meetup
     * @return updated meetup
     */
    @Override
    public Meetup updateMeetup(final int meetupID,
        final Meetup editedMeetup,
        final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        return updateMeetup(meetupID, editedMeetup, user);
    }

    /**
     * Update meetup.
     *
     * @param meetupID id of meetup to update
     * @param editedMeetup Object, to be added to database.
     * @param userID id of user that creates a meetup
     * @return updated meetup
     */
    @Override
    public Meetup updateMeetup(final int meetupID,
        final Meetup editedMeetup,
        final int userID) {
        User user = userDao.findUserById(userID);
        return updateMeetup(meetupID, editedMeetup, user);
    }

    /**
     * Update meetup.
     *
     * @param meetupID id of meetup to update
     * @param editedMeetup Object, to be added to database.
     * @param user user that updates meetup
     * @return updated meetup
     */
    private Meetup updateMeetup(final int meetupID,
        final Meetup editedMeetup,
        final User user) {
        Meetup existingMeetup = meetupDao.findMeetupByID(meetupID);
        if (isSpeaker(user) && user.getId() == existingMeetup.getSpeakerId()) {
            editedMeetup.setSpeakerId(user.getId());
            if (editedMeetup.getStateId() == 0) {
                editedMeetup.setStateId(existingMeetup.getStateId());
            }
            Meetup updated = meetupDao.updateMeetup(editedMeetup, meetupID);
            String changes = getMeetupChanges(existingMeetup, updated);
            if (!changes.isEmpty()) {
                notificationService
                    .sendMeetupInfoChangedNotifications(updated, changes);
            }
            return updated;
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

    /**
     * Cancel existing meetup.
     *
     * @param meetupID Meetup ID to be canceled
     * @param login User login (that removes the meetup)
     * @return cancelled meetup
     */
    @Override
    public Meetup cancelMeetup(final int meetupID, final String login) {
        User user = userDao.findUserByLogin(login);
        return cancelMeetup(meetupID, user);
    }

    /**
     * Cancel existing meetup.
     *
     * @param meetupID Meetup ID to be canceled
     * @param userID User id (that removes the meetup)
     * @return cancelled meetup
     */
    @Override
    public Meetup cancelMeetup(final int meetupID, final int userID) {
        User user = userDao.findUserById(userID);
        return cancelMeetup(meetupID, user);
    }

    /**
     * Cancel existing meetup.
     *
     * @param meetupID Meetup ID to be canceled
     * @param user user that removes the meetup
     * @return cancelled meetup
     */
    private Meetup cancelMeetup(final int meetupID, final User user) {
        //TODO null meetup handling
        Meetup existingMeetup = meetupDao.findMeetupByID(meetupID);
        if (isSpeaker(user)) {
            existingMeetup.setStateId(MeetupState.CANCELED.getCode());
            Meetup updated = updateMeetup(meetupID, existingMeetup,
                existingMeetup.getSpeakerId());
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
    public MeetupDisplayDTO getMeetup(final int meetupID) {
        return meetupDao.findDisplayMeetupByID(meetupID);
    }

    /**
     * Start meetup for fixed duration.
     *
     * @param meetupID Meetup ID.
     * @param userLogin User login.
     * @return started meetup
     */
    @Override
    public Meetup startMeetup(final int meetupID, final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        Meetup currentMeetup = meetupDao.findMeetupByID(meetupID);
        if (isSpeaker(user)) {
            currentMeetup.setStateId(MeetupState.IN_PROGRESS.getCode());
            Meetup updated = updateMeetup(meetupID, currentMeetup,
                currentMeetup.getSpeakerId());
            runTimer(meetupID);
            return updated;
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

    /**
     * Terminate meetup.
     *
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
                == MeetupState.IN_PROGRESS.getCode()) {
                currentMeetup.setStateId(MeetupState.TERMINATED.getCode());
                return updateMeetup(meetupID, currentMeetup,
                    currentMeetup.getSpeakerId());
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
                        == MeetupState.IN_PROGRESS.getCode()) {
                        currentMeetup.setStateId(MeetupState.PASSED.getCode());
                        Meetup updated = meetupDao
                            .updateMeetup(currentMeetup, currentMeetup.getId());
                        notificationService
                            .sendLeaveFeedbackNotifications(updated);
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
    public List<MeetupDisplayDTO> getSpeakerMeetups(final int speakerID) {
        List<Meetup> speakerMeetups = meetupDao
            .getSpeakerMeetupsAllHosted(speakerID);
        if (speakerMeetups.isEmpty()) {
            throw new MeetupNotFoundException();
        }
        return meetupDTOConverter.convertToMeetupDTO(speakerMeetups);
    }

    /**
     * . List of meetups hosted by speaker in past
     *
     * @param id id of user
     * @return list  of user hosted meetups
     */
    @Override
    public List<Meetup> getHostedMeetupsPast(final int id) {
        return meetupDao.getSpeakerMeetupsPast(id);
    }

    /**
     * . List of meetups that will be hosted by this speaker
     *
     * @param id id of user
     * @return list of user hosted meetups
     */
    @Override
    public List<Meetup> getHostedMeetupsFuture(final int id) {
        return meetupDao.getSpeakerMeetupsFuture(id);
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
        if (currentMeetup.getStateId() == MeetupState.SCHEDULED.getCode()) {
            if (currentMeetup.getMaxAttendees() == usersOnMeetup.size() + 1) {
                currentMeetup.setStateId(MeetupState.BOOKED.getCode());
                notificationService.sendMeetupBookedNotification(currentMeetup);
            }
            meetupDao.addUserToMeetup(currentMeetup.getId(), user.getId());
            updateMeetup(currentMeetup.getId(), currentMeetup,
                currentMeetup.getSpeakerId());
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
        leaveMeetup(meetupID, user.getId());
    }

    /**
     * Remove user from specified meetup.
     *
     * @param meetupID Meetup, that will be used to remove user to
     * @param userID User's ID
     */
    @Override
    public void leaveMeetup(final int meetupID, final int userID) {
        Meetup currentMeetup = meetupDao.findMeetupByID(meetupID);
        meetupDao.removeUserFromMeetup(meetupID, userID);
        currentMeetup.setStateId(MeetupState.SCHEDULED.getCode());
        updateMeetup(currentMeetup.getId(), currentMeetup,
            currentMeetup.getSpeakerId());
    }

    /**
     * . Meetups that user attended in past
     *
     * @param id id
     * @return List of meetups
     */
    @Override
    public List<Meetup> getJoinedMeetupsPast(final int id) {
        return meetupDao.getUsersJoinedMeetupsPast(id);
    }

    /**
     * . Meetups that user will attend in  future
     *
     * @param id id
     * @return List of meetups
     */
    @Override
    public List<Meetup> getJoinedMeetupsFuture(final int id) {
        return meetupDao.getUsersJoinedMeetupsFuture(id);
    }

    /**
     * Get users registered on meetup.
     *
     * @param meetupId Meetup ID
     * @return List of users.
     */
    @Override
    public List<User> getUsersOnMeetup(final int meetupId) {
        return meetupDao.getUsersOnMeetup(meetupId);
    }

    private String getMeetupChanges(final Meetup oldMeetup,
        final Meetup newMeetup) {
        String result = "";
        if (oldMeetup.getStateId() != newMeetup.getStateId()) {
            MeetupState newState = MeetupState
                .getStateByID(newMeetup.getStateId());
            switch (newState) {
                case CANCELED:
                    result += NotificationConstants.MEETUP_CANCELLED_MESSAGE;
                    break;
                case TERMINATED:
                    result += NotificationConstants.MEETUP_TERMINATED_MESSAGE;
                    break;
                case SCHEDULED:
                case BOOKED:
                    result += NotificationConstants.MEETUP_RESCHEDULED_MESSAGE;
                    break;
                default:
                    break;
            }
        }
        if (!oldMeetup.getStartDate().equals(newMeetup.getStartDate())) {
            result += NotificationConstants.MEETUP_NEW_START_TIME_MESSAGE_START
                + newMeetup.getStartDate()
                .format(NotificationConstants.MEETUP_START_TIME_FORMATTER)
                + ".";
        }
        return result;
    }

    /**
     * Count the number of meetups in database.
     *
     * @return int number of all meetups
     */
    @Override
    public int getAllMeetupsCount() {
        return meetupDao.getAllMeetupsCount();
    }

    /**
     * Check if the number of meetup is in future user's meetups.
     *
     * @param meetupId id of meetup
     * @return boolean if user joined meetup
     */
    @Override
    public boolean ifJoinedMeetup(final int userId,final int meetupId) {
        return meetupDao.ifJoinedMeetup(userId,meetupId);
    }
}
