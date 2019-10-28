package com.meetup.service;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.utils.Pair;
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
     * Cancel existing meetup.
     *  @param meetupID Meetup ID to be canceled
     * @param login User login (that removes the meetup)
     * @return cancelled meetup
     */
    Meetup cancelMeetup(int meetupID, String login);

    /**
     * Updates an existing meetup.
     *
     * @param meetupID Meetup id, that should be returned.
     * @return Meetup object.
     */
    Meetup getMeetup(int meetupID);

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
     * . Register user for meetup.
     *
     * @param meetupID Meetup, that will be used to register user to
     * @param token JSON web token to extract user credentials
     */
    void joinMeetup(int meetupID, String token);

    /**
     * . Remove user for meetup.
     *
     * @param meetupID Meetup, that will be used to remove user to
     * @param token JSON web token to extract user credentials
     */
    void leaveMeetup(int meetupID, String token);

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
    List<Meetup>  getJoinedMeetupsFuture(final int id);
    /**
     * . get Meetups that user have hosted in past.
     * @param id int
     * @return List of Meetups
     */
    List<Meetup>  getHostedMeetupsPast( int id);
    /**
     * . get Meetups thatuser will host
     * @param id int
     * @return List of Meetups
     */
    List<Meetup>  getHostedMeetupsFuture( int id);
}
