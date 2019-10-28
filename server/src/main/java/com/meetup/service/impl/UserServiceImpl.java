package com.meetup.service.impl;

import com.meetup.entities.Meetup;
import com.meetup.entities.MeetupState;
import com.meetup.entities.Role;
import com.meetup.entities.User;
import com.meetup.entities.dto.ComplaintDTO;
import com.meetup.entities.dto.SimpleUserDTO;
import com.meetup.entities.dto.UserDTO;
import com.meetup.entities.dto.UserRegistrationDTO;
import com.meetup.error.EmailIsUsedException;
import com.meetup.error.LoginIsUsedException;
import com.meetup.error.UserNotFoundException;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.service.IMeetupService;
import com.meetup.service.IUserService;
import com.meetup.utils.UserDTOConverter;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * . Class for working with users
 */
@Component
public class UserServiceImpl implements IUserService {

    /**
     * . methods to DB considering users.
     */
    @Autowired
    private IUserDAO userDao;
    /**
     * .
     */
    @Autowired
    private IMeetupDAO meetupDao;
    /**
     * .
     */
    @Autowired
    private IMeetupService meetupService;

    /**
     * .
     *
     * @param user User (that has role of listener) to register
     */
    @Override
    public void registerAsListener(
            final UserRegistrationDTO user) {
        if (userDao.isLoginUsed(user.getLogin())) {
            throw new LoginIsUsedException();
        } else if (userDao.isEmailUsed(user.getEmail())) {
            throw new EmailIsUsedException();
        } else {
            user.getRoles().add(Role.LISTENER);
            userDao.insertNewUser(user);
        }
    }

    /**
     * .
     *
     * @param user User (that has role of speaker) to register
     */
    @Override
    public void registerAsSpeaker(
            final UserRegistrationDTO user) {
        if (userDao.isLoginUsed(user.getLogin())) {
            throw new LoginIsUsedException();
        } else if (userDao.isEmailUsed(user.getEmail())) {
            throw new EmailIsUsedException();
        } else {
            user.getRoles().addAll(Arrays.asList(Role.LISTENER, Role.SPEAKER));
            userDao.insertNewUser(user);
        }
    }

    /**
     * .
     *
     * @param user User
     * @return User, but with new profile
     */
    @Override
    public User updateProfile(final User user) {
        //TODO implement
        return null;
    }

    /**
     * .
     *
     * @param user User
     * @return User with new password
     */
    @Override
    public User changePassword(final User user) {
        //TODO implement
        return null;
    }

    /**
     * .
     *
     * @param login String
     * @return UserDTO
     */
    @Override
    public UserDTO getProfileUserDTO(final String login)  {
        User us = userDao.findUserByLogin(login);
        if (us == null) {
            throw new UserNotFoundException();
        }
        UserDTOConverter converter = new UserDTOConverter();
        UserDTO dtouser = converter.convertToUserDTO(us);
        dtouser.setLanguages(userDao.getUsersLanguages(us.getId()));
        return dtouser;
    }


    /**
     *
     * Get all active speakers.
     * @return List<User> of speakers.
     */
    @Override
    public List<User> getAllSpeakers() {
        return userDao.getAllSpeakers();
    }

    /**
     * Get all active users.
     * @return
     * List<User> of users.
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    /**
     * .
     *
     * @param id id
     * @return List of speakers, that user is subscripted to
     */
    @Override
    public List<User> getUsersSubscriptionsToSpeakers(final int id) {
        return userDao.getUsersSubscriptionsToSpeakers(id);
    }

    /**
     * .     *
     * remove users from hosted meetups & remove this user from all meetups he is subscribed to
     *
     * @param id user id
     * @return boolean whether successful operation or not
     */
    @Override
    public boolean deactivateUser(final int id) {
        //unsubscribe from future meetups
        for (Meetup meet : meetupService.getJoinedMeetupsFuture(id)) {
            meetupDao.removeUserFromMeetup(meet.getId(), id);
        }
        for (Meetup meetup : meetupDao.getSpeakerMeetupsFuture(id)) {
            for (User user : meetupDao.getUsersOnMeetup(meetup.getId())) {
                meetupDao.removeUserFromMeetup(meetup.getId(), user.getId());
            }
            // cancel Meetup a
            meetup.setStateId(MeetupState.CANCELED.getCode());
            meetupDao.updateMeetup(meetup, meetup.getId());
        }
        userDao.deactivateUser(id);
        return true;
    }

    /**
     *
     * @return List of complaints from DB.
     */
    @Override
    public List<ComplaintDTO> getAllNotReadComplaints() {
        return userDao.getAllNotReadComplaints();
    }

    /**
     * @throws UserNotFoundException if no user with this login
     * @param complaintDTO complaint without source
     * @param login login of source
     */
    @Override
    public void postComplaintOn(final ComplaintDTO complaintDTO, final String login) throws UserNotFoundException{
        User u = userDao.findUserByLogin(login);
        if (u == null) {
            throw new UserNotFoundException();
        }
        int id_source = u.getId();
        complaintDTO.setId_user_from(id_source);
        userDao.postComplaintOn(complaintDTO);
    }

    /**
     *
     * @param id Complaint id.
     * @return boolean whether is successfull
     */
    @Override
    public boolean markAsReadComplaint(final int id) {
        return userDao.markAsReadComplaint(id);
    }
    /**
     * User can subscribe on speaker.
     * @param userId who is subscriber
     * @param speakerId on whom user subscribes
     */
     @Override
    public void subscribeToSpeaker(final int userId, final int speakerId) {
         User u = userDao.findUserById(userId);
        if (u == null) {
             throw new UserNotFoundException();
         }
      userDao.subscribeToSpeaker(userId, speakerId);
    }
    /**
     * User can unsubscribe from speaker.
     * @param userId who is subscriber
     * @param speakerId on whom user subscribes
     */
    @Override
    public void unSubscribeFromSpeaker(int userId, int speakerId) {
        User u = userDao.findUserById(userId);
        if (u == null) {
            throw new UserNotFoundException();
        }
        userDao.unSubscribeFromSpeaker(userId, speakerId);
    }

    /**
     * Get list of subscribers of a given speaker (by his ID).
     * @param speakerId speaker
     * @return list of subscribers
     */
    @Override
    public List<User> getSubscribersOfSpeaker(int speakerId) {
        return userDao.getSubscribersOfSpeaker(speakerId);
    }

    /**
     * Get basic info about users ho are subscribed on speaker.
     * @param speakerId speaker
     * @return List of SimpleUserDTO
     */
    @Override
    public List<SimpleUserDTO> getSimpleSubscribersOfSpeaker(int speakerId) {
        return userDao.getSimpleSubscribersOfSpeaker(speakerId);
    }


}
