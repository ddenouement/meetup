package com.meetup.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Time convertion utility class.
 */
public final class TimeUtility {

    /**
     * Private utility class constructor.
     */
    private TimeUtility() {

    }

    /**
     * Helper method to convert SQL Timestamp to LocalDateTime.
     *
     * @param ts timestamp to convert
     * @return corresponding LocalDateTime object
     */
    public static LocalDateTime convertToLocalDateTime(final Timestamp ts) {
        if (ts != null) {
            return ts.toLocalDateTime();
        }
        return null;
    }
}
