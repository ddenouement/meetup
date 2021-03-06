package com.meetup.service;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.entities.dto.MeetupDisplayDTO;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Meetup service interface. Used to manage meetup functionality.
 */
public interface IMeetupService {

    /**
     * Creates a new meetup and adds it to database.
     *
     * @param meetup Meetup to be created
     * @param login User login (that creates the meetup)
     * @return created meetup
     */
    Meetup createMeetup(Meetup meetup, String login);

    /**
     * Updates an existing meetup.
     *
     * @param meetupID Meetup to be updated
     * @param editedMeetup Edited meetup.
     * @param login User login (that updates the meetup)
     * @return updated meetup
     */
    Meetup updateMeetup(int meetupID, Meetup editedMeetup, String login);

    /**
     * Updates an existing meetup.
     *
     * @param meetupID Meetup to be updated
     * @param editedMeetup Edited meetup.
     * @param userID User ID (that updates the meetup)
     * @return updated meetup
     */
    Meetup updateMeetup(int meetupID, Meetup editedMeetup, int userID);

    /**
     * Cancel existing meetup.
     *  @param meetupID Meetup ID to be canceled
     * @param login User login (that removes the meetup)
     * @return cancelled meetup
     */
    Meetup cancelMeetup(int meetupID, String login);

    /**
     * Cancel existing meetup.
     * @param meetupID Meetup ID to be canceled
     * @param userID User ID (that removes the meetup)
     * @return cancelled meetup
     */
    Meetup cancelMeetup(int meetupID, int userID);

    /**
     * Updates an existing meetup.
     *
     * @param meetupID Meetup id, that should be returned.
     * @return Meetup object.
     */
    MeetupDisplayDTO getMeetup(int meetupID);

    /**
     * Start meetup for fixed duration.
     *  @param meetupID Meetup ID.
     * @param userLogin User login.
     * @return started meetup
     */
    Meetup startMeetup(int meetupID, String userLogin);

    /**
     * Terminate meetup .
     *  @param meetupID Meetup ID.
     * @param userLogin User login.
     * @return terminated meetup
     */
    Meetup terminateMeetup(int meetupID, String userLogin);

    /**
     * Retrieve all available meetups.
     *
     * @return List of all meetups
     */
    List<MeetupDisplayDTO> getAllMeetups();
    /**
     * Retrieve all available meetups.
     *
     * @return List of all meetups
     */
    List<MeetupDisplayDTO> getMeetupsByPages(Integer offset, Integer limit);


    /**
     * Retrieve all meetups from database that start at the specified time.
     *
     * @param startTime start of meetup
     * @return List of meetups
     */
    List<Meetup> getMeetupsByStartTime(LocalDateTime startTime);

    /**
     * Get all meetups of specified speaker.
     *
     * @param speakerID Speaker ID
     * @return List of all meetups of specified speaker
     */
    List<MeetupDisplayDTO> getSpeakerMeetups(int speakerID);


    /**
     * Register user for meetup.
     *
     * @param meetupID Meetup, that will be used to register user to
     * @param userLogin user's login
     */
    void joinMeetup(int meetupID, String userLogin);

    /**
     * Remove user for meetup.
     *
     * @param meetupID Meetup, that will be used to remove user to
     * @param userLogin user's login
     */
    void leaveMeetup(int meetupID, String userLogin);

    /**
     * Remove user for meetup.
     *
     * @param meetupID Meetup, that will be used to remove user to
     * @param userID user's id
     */
    void leaveMeetup(int meetupID, int userID);

    /**
     * . get Meetups that userhave attended in past.
     * @param id int
     * @return List of Meetups
     */
    List<Meetup> getJoinedMeetupsPast(int id);
    /**
     * . get Meetups that will attend  in future.
     * @param id int
     * @return List of Meetups
     */
    List<Meetup>  getJoinedMeetupsFuture(int id);
    /**
     * . get Meetups that user have hosted in past.
     * @param id int
     * @return List of Meetups
     */
    List<Meetup>  getHostedMeetupsPast(int id);
    /**
     * . get Meetups thatuser will host
     * @param id int
     * @return List of Meetups
     */
    List<Meetup>  getHostedMeetupsFuture(int id);

    /**
     * Get users registered on meetup.
     *
     * @param meetupId Meetup ID
     * @return List of users.
     */
    List<User> getUsersOnMeetup(int meetupId);
    /**
     * Get number of joined users on meetup.
     *
     * @param meetupId Meetup ID
     * @return int count of users
     */
    int getJoinedUsersCount(int meetupId);
    /**
     * Count the number of meetups in database.
     *
     * @return int number of all meetups
     */
    int getAllMeetupsCount();
    /**
     * Check if the number of meetup is in future user's meetups.
     *
     * @param userId id of user
     * @param meetupId id of meetup
     * @return boolean if user joined meetup
     */
    boolean ifJoinedMeetup(int userId, int meetupId);
    void cancelOutdatedMeetups();

}
