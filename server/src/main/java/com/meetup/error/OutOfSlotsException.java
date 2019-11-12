package com.meetup.error;

/**
 * OutOfSlotsException class.
 * Thrown in case of reaching maximum number of slots in Meetup.
 */
public class OutOfSlotsException extends RuntimeException {

    /**
     * Constructor.
     */
    public OutOfSlotsException() {
        super();
    }
}
