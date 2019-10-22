package com.meetup.entities;

import lombok.Data;

@Data
public class Pair<F, S> {
    /** First element in pair. */
    private F first;
    /** Second element in pair. */
    private S second;

    /**
     * Construct pair.
     * @param first first element
     * @param second second element
     */
    public Pair(final F first, final S second) {
        this.first = first;
        this.second = second;
    }
}
