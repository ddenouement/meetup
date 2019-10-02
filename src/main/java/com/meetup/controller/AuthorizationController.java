package com.meetup.controller;

import com.meetup.entities.Admin;
import com.meetup.entities.Listener;
import com.meetup.entities.Speaker;
import com.meetup.entities.User;
import com.meetup.repository.impl.AdminDaoImpl;
import com.meetup.repository.impl.ListenerDaoImpl;
import com.meetup.repository.impl.SpeakerDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AuthorizationController {

    @Autowired
    ListenerDaoImpl listenerDao;
    @Autowired
    SpeakerDaoImpl speakerDao;
    @Autowired
    AdminDaoImpl adminDao;

//    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
//    public String test(@RequestBody User user) {
//        System.out.println(user);
//        return "kek";
//    }

    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    public ResponseEntity registerListener(@RequestBody Listener listener) {
        if (isUsedLoginListener(listener)){
            throw new BadCredentialsException("User with login: " + listener.getLogin() + " already exists!");
        }


        System.out.println(listener.toString());
        listenerDao.insertListener(listener);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "Listener registered successfully");
        return ok(model);
    }

    @RequestMapping(value = "/register/speaker", method = RequestMethod.POST)
    public ResponseEntity registerSpeaker(@RequestBody Speaker speaker) {
        if (isUsedLoginSpeaker(speaker)){
            throw new BadCredentialsException("User with login: " + speaker.getLogin() + " already exists!");
        }
        speakerDao.insertSpeaker(speaker);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "Speaker registered successfully");
        return ok(model);
    }











    private boolean isUsedLoginListener(Listener toRegisterListener) {
        //TODO rewrite this block
        //TODO implement find method in repo
        List<Listener> listenersRegistered = listenerDao.getAllListeners();
        List<Speaker> speakersRegistered = speakerDao.getAllSpeakers();
        List<Admin> adminsRegistered = adminDao.getAllAdmins();
        for (Listener l: listenersRegistered){
            if (l.getLogin().equals(toRegisterListener.getLogin())){
                return true;
            }
        }
        for (Speaker s: speakersRegistered){
            if (s.getLogin().equals(toRegisterListener.getLogin())){
                return true;
            }
        }
        for (Admin a: adminsRegistered){
            if (a.getLogin().equals(toRegisterListener.getLogin())){
                return true;
            }
        }
        return false;
    }

    private boolean isUsedLoginSpeaker(Speaker toRegisterSpeaker) {
        //TODO rewrite this block
        //TODO implement find method in repo
        List<Listener> listenersRegistered = listenerDao.getAllListeners();
        List<Speaker> speakersRegistered = speakerDao.getAllSpeakers();
        List<Admin> adminsRegistered = adminDao.getAllAdmins();
        for (Listener l: listenersRegistered){
            if (l.getLogin().equals(toRegisterSpeaker.getLogin())){
                return true;
            }
        }
        for (Speaker s: speakersRegistered){
            if (s.getLogin().equals(toRegisterSpeaker.getLogin())){
                return true;
            }
        }
        for (Admin a: adminsRegistered){
            if (a.getLogin().equals(toRegisterSpeaker.getLogin())){
                return true;
            }
        }
        return false;
    }
}