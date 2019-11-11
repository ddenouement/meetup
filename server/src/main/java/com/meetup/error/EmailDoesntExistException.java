package com.meetup.error;

/**
 * ElementIsUsedException class.
 */
public class EmailDoesntExistException extends RuntimeException {

    /**
     * Constructor.
     */
    public EmailDoesntExistException() {
        super("User with this email doesn't exist!");
    }
}
