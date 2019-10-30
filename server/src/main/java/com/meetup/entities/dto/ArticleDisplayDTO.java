package com.meetup.entities.dto;

import com.meetup.entities.Topic;
import lombok.Data;

/**
 * Data transfer object to be displayed.
 */
@Data
public class ArticleDisplayDTO {

    private String title;
    private String content;
    private Topic[] topics;
    private String dateTimePosted;
    private UserDTO author;

}
