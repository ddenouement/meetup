package com.meetup.utils;

import org.springframework.security.core.GrantedAuthority;

/**
 * Represents a Role in the system.
 */
public enum Role implements GrantedAuthority {
    /** Listener. */
    LISTENER,
    /** Speaker. */
    SPEAKER,
    /** Admin.*/
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
