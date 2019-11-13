package com.meetup.entities.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * CommentaryDisplayDTO class.
 */
@Data
public class CommentaryDisplayDTO {

    /**
     * ID of commentary.
     */
    private int id;
    /**
     * Author ID.
     */
    private int authorID;
    /**
     * Author login.
     */
    private String authorLogin;
    /**
     * Article ID.
     */
    private int articleID;
    /**
     * Contents of commentary.
     */
    private String contents;
    /**
     * Date and time of posting commentary .
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timePosted;
}
