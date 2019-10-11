package com.meetup.service;

import com.meetup.entities.User;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> registerAsListener(User user);

    ResponseEntity<String> registerAsSpeaker(User user);

    User updateProfile(User user);

    User changePassword(User user);

    User getProfile(String login);

    List<User> getAllSpeakers();

}
