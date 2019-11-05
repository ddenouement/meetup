package com.meetup.entities;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Commentary class.
 */
@Data
public class Commentary {

    /**
     * ID of commentary.
     */
    private int id;
    /**
     * Author ID.
     */
    private int authorID;
    /**
     * Article ID.
     */
    private int articleID;
    /**
     * Contents of commentary.
     */
    private String contents;

    /**
     * Date and time in number format (from front)
     */
    private long timePostedNumeric;
    /**
     * Date and time of posting commentary.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timePosted;
}