package com.meetup.error;

/**
 * LoginIsUsedException class.
 * Thrown in case if user tries to register with login,
 * that already exists.
 */
public class LoginIsUsedException extends ElementIsUsedException {

    /**
     * Constructor.
     */
    public LoginIsUsedException() {
        super();
    }
}
