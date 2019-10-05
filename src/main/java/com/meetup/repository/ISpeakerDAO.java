package com.meetup.repository;

import com.meetup.entities.Speaker;

import java.util.List;

public interface ISpeakerDAO {

    List<Speaker> getAllSpeakers();

    void insertSpeaker(Speaker emp);

    Speaker findSpeakerByLogin(String login);
}
