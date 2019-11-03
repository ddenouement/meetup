package com.meetup.error;

/**
 * ElementIsUsedException class.
 */
public class ElementIsUsedException extends RuntimeException {

    /**
     * Constructor.
     */
    public ElementIsUsedException() {
        super("Element is already used!");
    }
}
