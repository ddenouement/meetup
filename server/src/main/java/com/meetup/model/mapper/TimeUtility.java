package com.meetup.model.mapper;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Time convertion utility class.
 */
public final class TimeUtility {

    /**
     * Private utility class constructor.
     */
    private TimeUtility(){

    }
    /**.
     * Helper method to convert SQL Date to LocalDateTime.
     * @param dateToConvert
     * SQL Date value
     * @return
     * LocalDateTime object
     */
    public static LocalDateTime convertToLocalDateTimeViaSqlTimestamp(
        final Date dateToConvert) {
        return new java.sql.Timestamp(
            dateToConvert.getTime()).toLocalDateTime();
    }
}
