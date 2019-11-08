package com.meetup.error;

/**
 * LanguageIsUsedException class. Thrown in case if user tries to add topic with
 * name, that already exists.
 */
public class LanguageIsUsedException extends ElementIsUsedException {

    /**
     * Constructor.
     */
    public LanguageIsUsedException() {
        super();
    }
}
