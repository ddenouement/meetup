package com.meetup.service;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import java.util.List;

/**
 * Meetup service interface.
 * Used to manage meetup functionality.
 */
public interface MeetupService {

    /**
     * Creates a new meetup and adds it to database.
     * @param meetup
     * Meetup to be created
     * @param login
     * User login (that creates the meetup)
     * @return
     * Created meetup
     * @throws IllegalAccessException
     * If meetup is created not by speaker, IllegalAccessException is thrown.
     */
    Meetup createMeetup(Meetup meetup, String login)
        throws IllegalAccessException;

    /**
     * Retrieve all available topics.
     * @param login
     * User login (that retrieves topics)
     * @return
     * List of all topics
     */
    List<Topic> getAllTopics(String login);

    /**
     * Retrieve all available meetups.
     * @param login
     * User login (that retrieves meetups)
     * @return
     * List of all meetups
     */
    List<Meetup> getAllMeetups(String login);

    /**
     * Get all meetups of specified speaker.
     * @param login
     * Speaker login
     * @return
     * List of all meetups of specified speaker
     * @throws IllegalAccessException
     * If meetups retrieved not by speaker, IllegalAccessException is thrown.
     */
    List<Meetup> getSpeakerMeetups(String login)
        throws IllegalAccessException;
}
