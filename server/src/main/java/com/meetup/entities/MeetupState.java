package com.meetup.entities;

import lombok.Getter;

/**
 * Meetup state enum.
 */
@Getter
public enum MeetupState {

    /**
     * Scheduled.
     */
    SCHEDULED(2),
    /**
     * Booked.
     */
    BOOKED(3),
    /**
     * Canceled.
     */
    CANCELED(4),
    /**
     * In progress.
     */
    IN_PROGRESS(5),
    /**
     * Terminated.
     */
    TERMINATED(6),
    /**
     * Passed.
     */
    PASSED(7);

    /**
     * Code of state.
     */
    private int code;

    /**
     * Constructor.
     *
     * @param code Code of state.
     */
    MeetupState(final int code) {
        this.code = code;
    }

    /**
     * Get MeetupState enum instance by ID.
     * @param id
     * ID of state.
     * @return
     * MeetupState enum instance.
     */
    public static MeetupState getStateByID(int id) {
        for(MeetupState state : values()) {
            if(state.code == id) {
                return state;
            }
        }
        throw new IllegalStateException();
    }
}
