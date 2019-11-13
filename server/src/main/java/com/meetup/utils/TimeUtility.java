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

    public static LocalDateTime toZone(final LocalDateTime time, final ZoneId fromZone, final ZoneId toZone) {
        final ZonedDateTime zonedtime = time.atZone(fromZone);
        final ZonedDateTime converted = zonedtime.withZoneSameInstant(toZone);
        return converted.toLocalDateTime();
    }

    public static LocalDateTime toZone(final LocalDateTime time, final ZoneId toZone) {
        return toZone(time, ZoneId.systemDefault(), toZone);
    }

    public static LocalDateTime toUtc(final LocalDateTime time, final ZoneId fromZone) {
        return toZone(time, fromZone, ZoneOffset.UTC);
    }

    public static LocalDateTime toUtc(final LocalDateTime time) {
        return toUtc(time, ZoneId.systemDefault());
    }
}
