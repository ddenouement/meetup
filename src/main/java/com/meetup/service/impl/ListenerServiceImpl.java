package com.meetup.service.impl;

import com.meetup.entities.Listener;
import com.meetup.repository.impl.AdminDaoImpl;
import com.meetup.repository.impl.ListenerDaoImpl;
import com.meetup.repository.impl.SpeakerDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.ListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ListenerServiceImpl implements ListenerService {

    @Autowired
    ListenerDaoImpl listenerDao;
    @Autowired
    SpeakerDaoImpl speakerDao;
    @Autowired
    AdminDaoImpl adminDao;
    @Autowired
    UserDaoImpl userDao;

    @Override
    public ResponseEntity<String> login(Listener listener) {
        //TODO implement
        return null;
    }

    @Override
    public ResponseEntity<String> register(Listener listener) {
        if(userDao.isLoginUsed(listener.getLogin())||userDao.isEmailUsed(listener.getEmail()))   return new ResponseEntity<>("User already exists", HttpStatus.FORBIDDEN);
        else {
            userDao.insertNewUser(listener);
            return new ResponseEntity<>("Registered successful", HttpStatus.CREATED);
        }
    }

    @Override
    public Listener updateProfile(Listener listener) {
        //TODO implement
        return null;
    }

    @Override
    public Listener changePassword(Listener listener) {
        //TODO implement
        return null;
    }
}
