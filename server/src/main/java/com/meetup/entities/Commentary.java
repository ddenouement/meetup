package com.meetup.entities;

import com.meetup.entities.dto.UserDTO;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timePosted;
}
