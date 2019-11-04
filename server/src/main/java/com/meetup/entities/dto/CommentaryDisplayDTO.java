package com.meetup.entities.dto;

import lombok.Data;

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
    private UserDTO author;
    /**
     * Article ID.
     */
    private int articleID;
    /**
     * Contents of commentary.
     */
    private String contents;
    /**
     * Date and time of posting commentary.
     */
    private String timePosted;
}
