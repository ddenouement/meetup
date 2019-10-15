package com.meetup.service.impl;

import com.meetup.entities.Language;
import com.meetup.entities.User;
import com.meetup.entities.UserDTO;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.service.IUserService;
import com.meetup.service.RoleProcessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * . Class for working with users
 */
@Component
public class UserServiceImpl implements IUserService {

    //TODO enum
    /**
     * . role
     */
    private static final String LISTENER = "LISTENER";
    /**
     * . role
     */
    private static final String SPEAKER = "SPEAKER";
    /**
     * . role
     */
    private static final String ADMIN = "ADMIN";
    /**
     * . methods to DB considering users.
     */
    @Autowired
    private IUserDAO userDao;
    @Autowired
    private IMeetupDAO meetupDao;

    /**
     * .
     *
     * @param user User (that has role of listener) to register
     * @return Entity, representing information about register status
     */
    @Override
    public ResponseEntity<String> registerAsListener(final User user) {
        if (userDao.isLoginUsed(user.getLogin()) || userDao
            .isEmailUsed(user.getEmail())) {
            return new ResponseEntity<>("User already exists",
                HttpStatus.FORBIDDEN);
        } else {
            user.getRoles().add(LISTENER);
            userDao.insertNewUser(user, new ArrayList<>());
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    /**
     * .
     *
     * @param user User (that has role of speaker) to register
     * @return Entity, representing information about register status
     */
    @Override
    public ResponseEntity<String> registerAsSpeaker(final User user,
        final List<Language> languages) {
        if (userDao.isLoginUsed(user.getLogin()) || userDao
            .isEmailUsed(user.getEmail())) {
            return new ResponseEntity<>("User already exists",
                HttpStatus.FORBIDDEN);
        } else {
            user.getRoles().addAll(Arrays.asList(LISTENER, SPEAKER));
            userDao.insertNewUser(user, languages);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    /**
     * .
     *
     * @param user User
     * @return User, but with new profile
     */
    @Override
    public User updateProfile(User user) {
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
    public User changePassword(User user) {
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
        return convertToUserDTO(us);

    }

    private UserDTO convertToUserDTO(User us) {
        UserDTO newUser = new UserDTO();
        newUser.setAbout(us.getAbout());
        newUser.setActive(us.isActive());
        newUser.setEmail(us.getEmail());
        newUser.setId(us.getId());
        newUser.setFirstName(us.getFirstName());
        newUser.setLastName(us.getLastName());
        newUser.setRoles(us.getRoles());
        newUser.setLogin(us.getLogin());
        newUser.setRate(us.getRate());
        if (RoleProcessor.isSpeaker(us)) {
            newUser.setHosted(meetupDao.getSpeakerMeetups(us.getId()));
        }
        newUser.setJoined(meetupDao.getUsersJoinedMeetups(us.getId()));
        newUser.setSubscriptedToSpeakers(
            userDao.getUsersSubscriptionsToSpeakers(us.getId()));
        newUser.setLanguages(userDao.getUsersLanguages(us.getId()));

        return newUser;
    }


    /**
     * .
     *
     * @return List<User> of speakers
     */
    @Override
    public List<User> getAllSpeakers() {
        //TODO implement
        return null;
    }


}
