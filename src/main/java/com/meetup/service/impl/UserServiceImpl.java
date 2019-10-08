package com.meetup.service.impl;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.repository.impl.TopicDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private static final String LISTENER = "LISTENER";
    private static final String SPEAKER = "SPEAKER";
    private static final String ADMIN = "ADMIN";

    @Autowired
    TopicDaoImpl topicDao;
    @Autowired
    UserDaoImpl userDao;


    @Override
    public ResponseEntity<String> registerAsListener(User user) {
        if (userDao.isLoginUsed(user.getLogin()) || userDao.isEmailUsed(user.getEmail())) {
            return new ResponseEntity<>("User already exists", HttpStatus.FORBIDDEN);
        } else {
            user.getRoles().add(LISTENER);
            System.out.println(user.toString());
            userDao.insertNewUser(user);
            return new ResponseEntity<>("Registered successful", HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<String> registerAsSpeaker(User user) {
        if (userDao.isLoginUsed(user.getLogin()) || userDao.isEmailUsed(user.getEmail())) {
            return new ResponseEntity<>("User already exists", HttpStatus.FORBIDDEN);
        } else {
            user.getRoles().add(LISTENER);
            user.getRoles().add(SPEAKER);
            System.out.println(user.toString());
            userDao.insertNewUser(user);
            return new ResponseEntity<>("Registered successful", HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<String> login(User user) {
        //TODO implement
        return null;
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
    public ResponseEntity<String> createMeeting(Meeting meeting, User user) {
        return null;
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicDao.getAllTopics();
    }
}
