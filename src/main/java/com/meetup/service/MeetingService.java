package com.meetup.service;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;

import java.util.List;

/**
 * @author Dmytro Zubko
 */

public interface MeetingService {
    Meeting createMeeting(Meeting meeting, String login);

    List<Topic> getAllTopics(String login);

    List<Meeting> getAllMeetings(String login);

    List<Meeting> getSpeakerMeetings(String login);
}
