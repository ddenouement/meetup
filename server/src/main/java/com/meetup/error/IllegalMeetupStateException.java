package com.meetup.error;

/**
 * IllegalMeetupStateException class. Thrown in case if user tries to perform
 * operation with meetup, which state is illegal.
 */
public class IllegalMeetupStateException extends RuntimeException {

    /**
     * Constructor.
     */
    public IllegalMeetupStateException() {
        super();
    }
}
