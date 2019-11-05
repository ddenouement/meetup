package com.meetup.error;

/**
 * BadgeScriptIsIncorrectException class.
 * Thrown if SQL script for badge is incorrect.
 */
public class BadgeNameExistsException extends RuntimeException {

    /**
     * Constructor.
     */
    public BadgeNameExistsException() {
        super("Badge with such name already exists.");
    }
}
