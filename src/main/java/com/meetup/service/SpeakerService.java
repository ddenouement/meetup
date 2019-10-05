package com.meetup.service;

import com.meetup.entities.Listener;
import com.meetup.entities.Speaker;
import org.springframework.http.ResponseEntity;

public interface SpeakerService {

    ResponseEntity<String> login(Speaker speaker);
    ResponseEntity<String> register(Speaker speaker);
    Speaker updateProfile(Speaker speaker);
    Speaker changePassword(Speaker speaker);



}
