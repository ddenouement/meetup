package com.meetup.error;

/**
 * SpeakerOperationNotAllowedException class.
 * Thrown in case if user without permission tries to operate
 * with functions of other user.
 */
public class SpeakerOperationNotAllowedException extends RuntimeException {

    /**
     * Constructor.
     */
    public SpeakerOperationNotAllowedException() {
        super("You are not allowed to do this operation!");
    }
}
