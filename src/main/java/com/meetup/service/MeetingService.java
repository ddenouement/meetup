package com.meetup.service;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Dmytro Zubko
 */

public interface MeetingService {
    ResponseEntity<String> createMeeting(Meeting meeting, User user);

    List<Topic> getAllTopics();

    List<Meeting> getAllMeetings();

    List<Meeting> getSpeakerMeetings(User user);
}
