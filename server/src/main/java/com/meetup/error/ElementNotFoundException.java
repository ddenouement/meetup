package com.meetup.error;

/**
 * ElementNotFoundException class.
 */
public class ElementNotFoundException extends RuntimeException {

    /**
     * Constructor.
     */
    public ElementNotFoundException() {
        super("Element not found!");
    }
}
