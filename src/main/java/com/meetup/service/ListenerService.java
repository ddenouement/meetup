package com.meetup.service;

import com.meetup.entities.Listener;
import org.springframework.http.ResponseEntity;

public interface ListenerService {

    ResponseEntity<String> login(Listener listener);

    ResponseEntity<String> register(Listener listener);

    Listener updateProfile(Listener listener);

    Listener changePassword(Listener listener);


}
