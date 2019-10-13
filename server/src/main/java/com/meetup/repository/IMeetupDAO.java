package com.meetup.repository;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import java.util.List;

public interface IMeetupDAO {

    /**
     * Retrieve all available meetups from database.
     * @return
     * List of all meetups
     */
    List<Meetup> getAllMeetups();

    /**
     * Add meetup to database
     * @param meetup
     * Meetup object to be added
     */
    void insertNewMeetup(Meetup meetup);

    /**
     * Retrieve meetups of specified speaker.
     * @param speakerID
     * Speaker ID
     * @return
     * List of meetups of specified speaker
     */
    List<Meetup> getSpeakerMeetups(int speakerID);

    /**
     * Add specific topic to database for meetup.
     * @param meetup
     * Meetup object, that should have topic
     * @param topic
     * Topic object that will be added to meetup
     */
    void addTopicToMeetup(Meetup meetup, Topic topic);

}
