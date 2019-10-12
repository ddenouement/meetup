package com.meetup.service;

/**
 * Interface, used to manage user login validity.
 */
public interface LoginValidatorService {

    /**
     * Method, used to extract user login from JWT.
     * @param token
     * JSON web token in String representation
     * @return
     * User login in String representation
     */
    String extractLogin(String token);

}
