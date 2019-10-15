package com.meetup.service;

import com.meetup.entities.Language;
import com.meetup.entities.User;
import com.meetup.entities.UserDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * . UserService interface. Working with abstract User
 */
public interface IUserService {

    /**
     * .
     *
     * @param user User
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> registerAsListener(User user);

    /**
     * Save user and his/her languages in storage.
     *
     * @param user User
     * @param languages user's native languages
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> registerAsSpeaker(User user,
        List<Language> languages);

    /**
     * .
     *
     * @param user User
     * @return User
     */
    User updateProfile(User user);

    /**
     * .
     *
     * @param user User
     * @return User
     */
    User changePassword(User user);

    /**
     * .
     *
     * @param login String
     * @return User
     */
    UserDTO getProfileUserDTO(String login);

    /**
     * .
     *
     * @return List<User> of speakers
     */
    List<User> getAllSpeakers();

}

