package com.meetup.service;

import com.meetup.entities.Listener;
import com.meetup.entities.Meeting;
import com.meetup.entities.Speaker;
import com.meetup.entities.Topic;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpeakerService {

    ResponseEntity<String> login(Speaker speaker);
    ResponseEntity<String> register(Speaker speaker);
    Speaker updateProfile(Speaker speaker);
    Speaker changePassword(Speaker speaker);
    ResponseEntity<String> createMeeting(Meeting meeting, Speaker speaker);
    List<Topic> getAllTopics();

}
