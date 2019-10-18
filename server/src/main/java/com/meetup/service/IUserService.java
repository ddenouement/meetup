package com.meetup.service;

import com.meetup.entities.User;
import com.meetup.entities.dto.UserDTO;
import com.meetup.entities.dto.UserRegistrationDTO;
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
    ResponseEntity<String> registerAsListener(UserRegistrationDTO user);

    /**
     * Save user and his/her languages in storage.
     *
     * @param user User
     * @return ResponseEntity<String>
     */
    ResponseEntity<String> registerAsSpeaker(UserRegistrationDTO user);

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

    /**.
     *
     * @param id id
     * @return List<User> of Speakers
     */
    List<User>  getUsersSubscriptionsToSpeakers(int id);

}

