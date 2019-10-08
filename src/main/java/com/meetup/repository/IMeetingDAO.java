package com.meetup.repository;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;

import java.util.List;

/**
 * @author Dmytro Zubko
 */

public interface IMeetingDAO {
    List<Meeting> getAllMeetings();

    void insertNewMeeting(Meeting meeting);

    List<Meeting> getSpeakerMeetings(int speakerID);

    void addTopicToMeeting(Meeting meeting, Topic topic);

}
