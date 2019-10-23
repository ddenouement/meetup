package com.meetup.error;

/**
 * BadgeScriptIsIncorrectException class.
 * Thrown if SQL script for badge is incorrect.
 */
public class BadgeScriptIsIncorrectException extends RuntimeException {

    /**
     * Constructor.
     */
    public BadgeScriptIsIncorrectException() {
        super("SQL script is incorrect.");
    }
}
