package com.meetup.service.impl;

import com.meetup.entities.Role;
import com.meetup.entities.User;
import com.meetup.entities.dto.UserDTO;
import com.meetup.entities.dto.UserRegistrationDTO;
import com.meetup.error.EmailIsUsedException;
import com.meetup.error.LoginIsUsedException;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.service.IUserService;
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
        return convertToUserDTO(us);

    }

    /**
     * . Convert a User exemplar to UserDTO exemplar (e.g. without password)
     *
     * @param us User
     * @return UserDTO
     */
    //TODO to utilities package
    private UserDTO convertToUserDTO(final User us) {
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


}
