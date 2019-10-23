package com.meetup.error;

public class UserNotFoundException extends RuntimeException {

    /**
     * Constructor.
     */
    public UserNotFoundException() {
        super("User not found!");
    }
}
