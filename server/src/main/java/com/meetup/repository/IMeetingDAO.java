package com.meetup.repository;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import java.util.List;

public interface IMeetingDAO {

    /**
     * Retrieve all available meetings from database
     * @return
     * List of all meetings
     */
    List<Meeting> getAllMeetings();

    /**
     * Add meeting to database
     * @param meeting
     * Meeting object to be added
     */
    void insertNewMeeting(Meeting meeting);

    /**
     * Retrieve meetings of specified speaker
     * @param speakerID
     * Speaker ID
     * @return
     * List of meetings of specified speaker
     */
    List<Meeting> getSpeakerMeetings(int speakerID);

    /**
     * Add specific topic to database for meeting
     * @param meeting
     * Meeting object, that should have topic
     * @param topic
     * Topic object that will be added to meeting
     */
    void addTopicToMeeting(Meeting meeting, Topic topic);

}
