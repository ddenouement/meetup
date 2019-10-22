package com.meetup.error;

/**
 * LoginIsUsedException class.
 * Thrown in case if user tries to register with login,
 * that already exists.
 */
public class LoginIsUsedException extends RuntimeException {

    /**
     * Constructor.
     */
    public LoginIsUsedException() {
        super("Login is already used!");
    }
}
