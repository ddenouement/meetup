package com.meetup.error;

/**
 * RateAlreadyExists exception class.
 * Thrown if user tries to rate meetup again..
 */
public class RateAlreadyExistsException extends RuntimeException {

    /**
     * Constructor.
     */
    public RateAlreadyExistsException(){
        super();
    }

}
