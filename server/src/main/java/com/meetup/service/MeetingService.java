package com.meetup.service;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import java.util.List;

public interface MeetingService {

    Meeting createMeeting(Meeting meeting, String login)
        throws IllegalAccessException;

    List<Topic> getAllTopics(String login);

    List<Meeting> getAllMeetings(String login);

    List<Meeting> getSpeakerMeetings(String login)
        throws IllegalAccessException;
}
