package com.meetup.service;

import com.meetup.entities.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<String> login(User user);

    ResponseEntity<String> registerAsListener(User user);

    ResponseEntity<String> registerAsSpeaker(User user);

    User updateProfile(User user);

    User changePassword(User user);

    ResponseEntity<String> createMeeting(Meeting meeting, User user);

    List<Topic> getAllTopics();

}
