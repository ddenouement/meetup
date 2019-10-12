package com.meetup.service;

import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import java.util.List;

public interface MeetupService {

    Meetup createMeetup(Meetup meetup, String login)
        throws IllegalAccessException;

    List<Topic> getAllTopics(String login);

    List<Meetup> getAllMeetups(String login);

    List<Meetup> getSpeakerMeetups(String login)
        throws IllegalAccessException;
}
