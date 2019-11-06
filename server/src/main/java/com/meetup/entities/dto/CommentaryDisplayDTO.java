package com.meetup.entities.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
     * Author.
     */
    private int authorID;
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
    private LocalDateTime timePosted;
}
