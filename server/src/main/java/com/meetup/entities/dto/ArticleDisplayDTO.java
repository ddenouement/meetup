package com.meetup.entities.dto;

import com.meetup.entities.Topic;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Data transfer object to be displayed.
 */
@Data
public class ArticleDisplayDTO {

    /**
     * Title of article.
     */
    private String title;
    /**
     * Content of article.
     */
    private String content;
    /**
     * List of topics of article.
     */
    private List<Topic> topics;
    /**
     * Date and time posted.
     */
    private String dateTimePosted;
    /**
     * Author of article.
     */
    private UserDTO author;

    /**
     * Init with empty lists.
     */
    public ArticleDisplayDTO() {
        this.topics = new ArrayList<>();
    }
}
