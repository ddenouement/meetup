package com.meetup.service.impl;

import com.meetup.entities.User;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Dmytro Zubko
 */

@Component
public class UserServiceImpl implements UserService {

    private static final String LISTENER = "LISTENER";
    private static final String SPEAKER = "SPEAKER";
    private static final String ADMIN = "ADMIN";


    @Autowired
    UserDaoImpl userDao;

    /**
     *
     * @param user
     * User (that has role of listener) to register
     * @return
     * Entity, representing information about register status
     */
    @Override
    public ResponseEntity<String> registerAsListener(User user) {
        if (userDao.isLoginUsed(user.getLogin()) || userDao.isEmailUsed(user.getEmail())) {
            return new ResponseEntity<>("User already exists", HttpStatus.FORBIDDEN);
        } else {
            user.getRoles().add(LISTENER);
            System.out.println(user.toString());
            userDao.insertNewUser(user);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    /**
     *
     * @param user
     * User (that has role of speaker) to register
     * @return
     * Entity, representing information about register status
     */
    @Override
    public ResponseEntity<String> registerAsSpeaker(User user) {
        if (userDao.isLoginUsed(user.getLogin()) || userDao.isEmailUsed(user.getEmail())) {
            return new ResponseEntity<>("User already exists", HttpStatus.FORBIDDEN);
        } else {
            user.getRoles().add(LISTENER);
            user.getRoles().add(SPEAKER);
            System.out.println(user.toString());
            userDao.insertNewUser(user);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }


    @Override
    public User updateProfile(User user) {
        //TODO implement
        return null;
    }

    @Override
    public User changePassword(User user) {
        //TODO implement
        return null;
    }

    @Override
    public User getProfile(String login) {
        return userDao.findUserByLogin(login);
    }


}
