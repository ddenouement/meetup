package com.meetup.service;

import com.meetup.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Dmytro Zubko
 */

public interface UserService {

    ResponseEntity<String> registerAsListener(User user);

    ResponseEntity<String> registerAsSpeaker(User user);

    User updateProfile(User user);

    User changePassword(User user);

    User getProfile(String login);

    List<User> getAllSpeakers();

}
