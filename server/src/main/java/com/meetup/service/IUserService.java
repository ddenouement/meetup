package com.meetup.service;

import com.meetup.entities.User;
import com.meetup.entities.dto.ComplaintDTO;
import com.meetup.entities.dto.SimpleUserDTO;
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
    List <ComplaintDTO>  getAllNotReadComplaints( );
    /**
     * .
     * @param compl ComplaintDTO
     */
    void postComplaintOn(ComplaintDTO compl, String login) throws UserNotFoundException;
    /**
     * mark complain as read.
     * @param id Complaint id
     */
    boolean markAsReadComplaint(int id);
    /**
     * for user to subscribe on speaker.
     * @param userId who is subscriber
     * @param speakerId on whom user subscribes
     */
    void subscribeToSpeaker(int userId, int speakerId);
    /**
     * unsubscribe from speaker.
     * @param userId who is subscriber
     * @param speakerId on whom user was subscribed
     */
    void unSubscribeFromSpeaker(int userId, int speakerId);

    /**
     * Get users who are subscribed on speaker.
     * @param speakerId speaker
     * @return users (subscribers)
     */
    List<User> getSubscribersOfSpeaker(int speakerId);

    /**
     * Get basic info about users who are subscribed on speaker.
     * @param speakerId speaker
     * @return users (subscribers)
     */
    List<SimpleUserDTO> getSimpleSubscribersOfSpeaker(int speakerId);

}

