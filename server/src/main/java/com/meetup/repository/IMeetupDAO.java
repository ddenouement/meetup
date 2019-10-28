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
     * @param currentMeetup Meetup object (edited)
     * @param meetupID meetup ID to be updated
     */
    void updateMeetup(Meetup currentMeetup, int meetupID);

    /**
     * Find meetup by id.
     * @param meetupID
     * Meetup id.
     * @return
     * Existing meetup.
     */
    Meetup findMeetupByID(int meetupID);

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
     * Add specific topic.ts to database for meetup.
     *
     * @param meetup Meetup object, that should have topic.ts
     * @param topic Topic object that will be added to meetup
     */
    void addTopicToMeetup(Meetup meetup, Topic topic);

    /**
     * Get topics of meetup.
     * @param meetupID
     * Meetup ID.
     * @return
     * List of topics.
     */
    List<Topic> getMeetupTopics(int meetupID);

    /**
     * Add user to specific meetup.
     *
     * @param meetupID Meetup, that user should register to.
     * @param userID User that takes part in meetup.
     */
    void addUserToMeetup(int meetupID, int userID);

    /**
     * Remove user from specific meetup.
     *
     * @param meetupID Meetup, that user should leave to.
     * @param userID User that leaves meetup.
     */
    void removeUserFromMeetup(int meetupID, int userID);

    /**
     * Remove all users from specific meetup.
     *
     * @param meetupID Meetup, that user should leave to.
     */
    void removeAllUsersFromMeetup(int meetupID);

    /**
     * Get users on meetup.
     * @param meetupId
     * Meetup ID
     * @return
     * List of users
     */
    List<User> getUsersOnMeetup(int meetupId);

}
