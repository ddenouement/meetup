package com.meetup.error;


/**
 * MeetupNotFoundException class.
 * Caused by getting non-existing meetup.
 */
//TODO ierarchy of exceptions
public class MeetupNotFoundException extends RuntimeException {

    /**
     * Constructor.
     */
    public MeetupNotFoundException() {
        super("Meetup not found!");
    }

}
