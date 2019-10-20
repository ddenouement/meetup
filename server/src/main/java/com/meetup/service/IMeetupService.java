package com.meetup.service;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import javafx.util.Pair;

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
     * @throws IllegalAccessException If meetup is created not by speaker, IllegalAccessException is thrown.
     */
    void createMeetup(Meetup meetup, String login)
            throws IllegalAccessException;

    /**
     * Updates an existing meetup.
     *
     * @param meetup Meetup to be updated
     * @param login  User login (that updates the meetup)
     * @throws IllegalAccessException If meetup is updated not by speaker, IllegalAccessException is thrown.
     */
    void updateMeetup(Meetup meetup, String login)
            throws IllegalAccessException;

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
     * @param login Speaker login
     * @return List of all meetups of specified speaker
     * @throws IllegalAccessException If meetups retrieved not by speaker, IllegalAccessException is thrown.
     */
    List<Meetup> getSpeakerMeetups(String login)
            throws IllegalAccessException;

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
     * @param meetup Meetup, that will be used to register user to
     * @param token  JSON web token to extract user credentials
     */
    void joinMeetup(Meetup meetup, String token);

    /**
     * .
     * Remove user for meetup.
     *
     * @param meetup Meetup, that will be used to remove user to
     * @param token  JSON web token to extract user credentials
     */
    void leaveMeetup(Meetup meetup, String token);

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
