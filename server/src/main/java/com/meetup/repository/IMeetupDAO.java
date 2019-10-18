package com.meetup.repository;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import java.util.List;

/**
 * Interface for meetup repository (DAO).
 */
public interface IMeetupDAO {

    /**
     * Retrieve all available meetups from database.
     *
     * @return List of all meetups
     */
    List<Meetup> getAllMeetups();

    /**
     * Add meetup to database.
     *
     * @param meetup Meetup object to be added
     */
    void insertNewMeetup(Meetup meetup);

    /**
     * Update existing meetup.
     *
     * @param meetup Meetup object to be updated
     */
    void updateMeetup(Meetup meetup);

    /**
     * Retrieve meetups of specified speaker.
     *
     * @param speakerID Speaker ID
     * @return List of meetups of specified speaker
     */
    List<Meetup> getSpeakerMeetups(int speakerID);

    /**
     * Retrieve meetups of specified user.
     *
     * @param userID User ID
     * @return List of meetups of specified user
     */
    List<Meetup> getUsersJoinedMeetups(int userID);

    /**
     * Add specific topic to database for meetup.
     *
     * @param meetup Meetup object, that should have topic
     * @param topic Topic object that will be added to meetup
     */
    void addTopicToMeetup(Meetup meetup, Topic topic);

    /**
     * Add user to specific meetup.
     *
     * @param meetup Meetup, that user should register to.
     * @param user User that takes part in meetup.
     */
    void addUserToMeetup(Meetup meetup, User user);

    /**
     * Remove user from specific meetup.
     *
     * @param meetup Meetup, that user should leave to.
     * @param user User that leaves meetup.
     */
    void removeUserFromMeetup(Meetup meetup, User user);

    /**
     * Get users on meetup.
     * @param meetupId
     * Meetup ID
     * @return
     * List of users
     */
    List<User> getUsersOnMeetup(int meetupId);

}
