package com.meetup.repository;

import com.meetup.entities.Listener;

import java.util.List;

public interface IListenerDAO {

    List<Listener> getAllListeners();
    void insertListener(Listener emp);
}
