package com.meetup.repository;

import com.meetup.entities.Feedback;
import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.entities.dto.MeetupDisplayDTO;
import java.time.LocalDateTime;
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
    List<MeetupDisplayDTO> getAllMeetups();

    /**
     * Count the number of meetups in database.
     *
     * @return List of all meetups
     */
    int getAllMeetupsCount();

    /**
     * Return all meetups with display using pagination parameters.
     * @param offset offset for paging
     * @param limit limit for paging
     * @return List of meetups.
     */
    List<MeetupDisplayDTO> getAllMeetupsByPages(Integer offset, Integer limit);

    /**
     * Retrieve all meetups from database that start at the specified time.
     *
     * @param startTime start of meetup
     * @return List of meetups
     */
    List<Meetup> getMeetupsByStartTime(LocalDateTime startTime);

    /**
     * Add meetup to database.
     *
     * @param meetup Meetup object to be added
     * @return added Meetup.
     */
    Meetup insertNewMeetup(Meetup meetup);

    /**
     * Update existing meetup.
     *
     * @param currentMeetup Meetup object (edited)
     * @param meetupID meetup ID to be updated
     * @return updated meetup
     */
    Meetup updateMeetup(Meetup currentMeetup, int meetupID);

    /**
     * Find meetup by id.
     *
     * @param meetupID Meetup id.
     * @return Existing meetup.
     */
    Meetup findMeetupByID(int meetupID);

    /**
     * Get display meetup from DB by ID.
     *
     * @param meetupID Meetup id.
     * @return MeetupDisplayDTO object.
     */
    MeetupDisplayDTO findDisplayMeetupByID(int meetupID);

    /**
     * Retrieve future hosted meetups of specified speaker.
     *
     * @param speakerID Speaker ID
     * @return List of meetups of specified speaker
     */
    List<Meetup> getSpeakerMeetupsPast(int speakerID);

    /**
     * Retrieve past hosted meetups of specified speaker.
     *
     * @param speakerID Speaker ID
     * @return List of meetups of specified speaker
     */
    List<Meetup> getSpeakerMeetupsFuture(int speakerID);

    /**
     * Retrieve past attended meetups of specified user.
     *
     * @param userID User ID
     * @return List of meetups of specified user
     */
    List<Meetup> getUsersJoinedMeetupsPast(int userID);

    /**
     * Retrieve future attended meetups of specified user.
     *
     * @param userID User ID
     * @return List of meetups of specified user
     */
    List<Meetup> getUsersJoinedMeetupsFuture(int userID);

    /**
     * Add specific topic.ts to database for meetup.
     *
     * @param meetup Meetup object, that should have topic.ts
     * @param topic Topic object that will be added to meetup
     */
    void addTopicToMeetup(Meetup meetup, Topic topic);

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
     *
     * @param meetupId Meetup ID
     * @return List of users
     */
    List<User> getUsersOnMeetup(int meetupId);

    List<Meetup> getSpeakerMeetupsAllHosted(int speakerID);

    /**
     * Rate specific meetup.
     *
     * @param meetupID Meetup ID.
     * @param userID User ID.
     * @param feedback Feedback object.
     */
    void rateMeetup(int meetupID, int userID, Feedback feedback);
    /**
     * Check if user joined specific meetup.
     *
     * @param userId User ID.
     * @param meetupId Meetup ID.
     * */
    boolean ifJoinedMeetup(int userId, int meetupId);
}
