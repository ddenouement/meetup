package com.meetup.service;

import com.meetup.entities.User;

import java.util.List;

public class RoleProcessor {

    public static boolean isSpeaker(User user) {
        List<String> roles = user.getRoles();
        for (String s : roles) {
            if (s.equals("SPEAKER")) {
                return true;
            }
        }
        return false;
    }
}
