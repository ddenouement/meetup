package com.meetup.service;

import com.meetup.entities.*;
import org.springframework.http.ResponseEntity;

/**
 * @author Dmytro Zubko
 */

public interface UserService {

    ResponseEntity<String> registerAsListener(User user);

    ResponseEntity<String> registerAsSpeaker(User user);

    User updateProfile(User user);

    User changePassword(User user);

}
