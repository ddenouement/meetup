package com.meetup.service;

import com.meetup.entities.User;
import com.meetup.entities.dto.ComplaintDTO;
import com.meetup.entities.dto.UserDTO;
import com.meetup.entities.dto.UserRegistrationDTO;
import com.meetup.error.UserNotFoundException;

import java.util.List;

/**
 * . UserService interface. Working with abstract User
 */
public interface IUserService {

    /**
     * .
     *
     * @param user User
     */
    void registerAsListener(UserRegistrationDTO user);

    /**
     * Save user and his/her languages in storage.
     *
     * @param user User
     */
    void registerAsSpeaker(UserRegistrationDTO user);

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
     * Get user's profile info.
     * @param login login of user to get
     * @return UserDTO with info
     * @throws UserNotFoundException throws if user does not exist
     */
    UserDTO getProfileUserDTO(String login) throws UserNotFoundException;

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
    /**
     * .
     * @param id user id
     * @return boolean whether successful operation or not
     */
    boolean deactivateUser(int id);

    /**
     * .
     * @return List of all complaints
     */
    List <ComplaintDTO>  getAllComplaints( );
    /**
     * .
     * @param compl ComplaintDTO
     *
     */
    void postComplaintOn(ComplaintDTO compl, String login) throws UserNotFoundException;

}

