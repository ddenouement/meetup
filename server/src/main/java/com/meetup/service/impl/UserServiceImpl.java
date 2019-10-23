package com.meetup.service.impl;

import com.meetup.entities.Meetup;
import com.meetup.entities.Role;
import com.meetup.entities.User;
import com.meetup.entities.dto.UserDTO;
import com.meetup.entities.dto.UserRegistrationDTO;
import com.meetup.error.EmailIsUsedException;
import com.meetup.error.LoginIsUsedException;
import com.meetup.error.UserNotFoundException;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.service.IMeetupService;
import com.meetup.service.IUserService;
import com.meetup.utils.MeetupStateConstants;
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
    /**.
     *
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
    public UserDTO getProfileUserDTO(final String login) {
        User us = userDao.findUserByLogin(login);
        if (us == null) {
            throw  new UserNotFoundException();
        }
        UserDTOConverter converter = new UserDTOConverter();
        UserDTO dtouser = converter.convertToUserDTO(us);
        dtouser.setLanguages(userDao.getUsersLanguages(us.getId()));
        return dtouser;
    }


    /**
     * .
     *
     * @return List<User> of speakers
     */
    @Override
    public List<User> getAllSpeakers() {
        //TODOo implement
        return null;
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
        for (Meetup meet : meetupService.getUserJoinedMeetups(id).getSecond()) {
            meetupDao.removeUserFromMeetup(meet.getId(), id);
        }
        for (Meetup meetup : meetupDao.getSpeakerMeetups(id)) {
            for (User user : meetupDao.getUsersOnMeetup(meetup.getId())) {
                meetupDao.removeUserFromMeetup(meetup.getId(), user.getId());
            }
            // cancel Meetup a
            meetup.setStateId(MeetupStateConstants.CANCELED);
            meetupDao.updateMeetup(meetup, meetup.getId());
        }
        userDao.deactivateUser(id);
        return true;
    }


}
