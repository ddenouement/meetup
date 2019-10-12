package com.meetup.service;

import com.meetup.entities.User;
import java.util.List;

/**
 * Service, used to manage user roles.
 */
public final class RoleProcessor {

    /**
     * Private constructor. (Utility class)
     */
    private RoleProcessor() {

    }

    /**
     * Returns true, if user is speaker.
     *
     * @param user User, used to check if it is speaker
     * @return boolean
     */
    public static boolean isSpeaker(final User user) {
        List<String> roles = user.getRoles();
        for (String s : roles) {
            if (s.equals("SPEAKER")) {
                return true;
            }
        }
        return false;
    }
}
