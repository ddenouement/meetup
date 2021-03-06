package com.meetup.error;

/**
 * EmailIsUsedException class.
 * Thrown in case if user tries to register with email,
 * that already exists.
 */
public class EmailIsUsedException extends ElementIsUsedException {

    /**
     * Constructor.
     */
    public EmailIsUsedException() {
        super();
    }
}
