package com.meetup.service;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.entities.Pair;

import java.util.List;

/**
 * Meetup service interface.
 * Used to manage meetup functionality.
 */
public interface IMeetupService {

    /**
     * Creates a new meetup and adds it to database.
     *
     * @param meetup Meetup to be created
     * @param login  User login (that creates the meetup)
     */
    void createMeetup(Meetup meetup, String login);

    /**
     * Updates an existing meetup.
     *
     * @param editedMeetup Edited meetup.
     * @param meetupID Meetup to be updated
     * @param login  User login (that updates the meetup)
     */
    void updateMeetup(int meetupID, Meetup editedMeetup, String login);

    /**
     * Cancel existing meetup.
     * @param meetupID Meetup ID to be canceled
     * @param login User login (that removes the meetup)
     */
    void cancelMeetup(int meetupID, String login);
    /**
     * Updates an existing meetup.
     *
     * @param meetupID Meetup id, that should be returned.
     * @return Meetup object.
     */
    Meetup getMeetup(int meetupID);

    /**
     * Retrieve all available topics.
     *
     * @return List of all topics
     */
    List<Topic> getAllTopics();

    /**
     * Retrieve all available meetups.
     *
     * @return List of all meetups
     */
    List<Meetup> getAllMeetups();

    /**
     * Get all meetups of specified speaker.
     *
     * @param speakerID Speaker ID
     * @return List of all meetups of specified speaker
     */
    List<Meetup> getSpeakerMeetups(int speakerID);

    /**
     * .
     * Get all meetups of specified speaker.
     * <Past; Future >
     *
     * @param login login of user
     * @return List of all meetups of specified speaker
     **/
    Pair<List<Meetup>, List<Meetup>> getSpeakerMeetupsByLogin(String login);

    /**
     * .
     * Register user for meetup.
     *
     * @param meetupID Meetup, that will be used to register user to
     * @param token  JSON web token to extract user credentials
     */
    void joinMeetup(int meetupID, String token);

    /**
     * .
     * Remove user for meetup.
     *
     * @param meetupID Meetup, that will be used to remove user to
     * @param token  JSON web token to extract user credentials
     */
    void leaveMeetup(int meetupID, String token);

    /**
     * .
     * get Meetups that user joined
     * <Past; Future>
     *
     * @param id int
     * @return List of Meetups
     */
    Pair<List<Meetup>, List<Meetup>> getUserJoinedMeetups(int id);

}
