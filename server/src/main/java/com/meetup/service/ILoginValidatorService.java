package com.meetup.service;

/**
 * Interface, used to manage user login validity.
 */
public interface ILoginValidatorService {

    /**
     * Method, used to extract user login from JWT.
     * @param token
     * JSON web token in String representation
     * @return
     * User login in String representation
     */
    String extractLogin(String token);
    /**
     * Method, used to extract user id from JWT.
     * @param token
     * JSON web token in String representation
     * @return
     * User id
     */
    int extractId(  String token);
}
