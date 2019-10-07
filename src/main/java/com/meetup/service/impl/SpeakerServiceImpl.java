package com.meetup.service.impl;

import com.meetup.entities.Listener;
import com.meetup.entities.Speaker;
import com.meetup.repository.impl.AdminDaoImpl;
import com.meetup.repository.impl.ListenerDaoImpl;
import com.meetup.repository.impl.SpeakerDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

@Component
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    ListenerDaoImpl listenerDao;
    @Autowired
    SpeakerDaoImpl speakerDao;
    @Autowired
    AdminDaoImpl adminDao;
    @Autowired
    UserDaoImpl userDao;


    @Override
    public ResponseEntity<String> login(Speaker speaker) {
        //TODO implement
        return null;
    }

    @Override
    public ResponseEntity<String> register(Speaker speaker) {
        if(userDao.isLoginUsed(speaker.getLogin())||userDao.isEmailUsed(speaker.getEmail()))   return new ResponseEntity<>("User already exists", HttpStatus.FORBIDDEN);
        else {
            System.out.println(speaker.toString());

            userDao.insertNewUser(speaker);

            return new ResponseEntity<>("Registered successful", HttpStatus.CREATED);
        }
    }

    @Override
    public Speaker updateProfile(Speaker speaker) {
        //TODO implement
        return null;
    }

    @Override
    public Speaker changePassword(Speaker speaker) {
        //TODO implement
        return null;
    }
}
