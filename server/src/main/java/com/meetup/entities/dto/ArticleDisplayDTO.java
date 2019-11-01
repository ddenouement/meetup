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

    private String title;
    private String content;
    private List<Topic> topics;
    private String dateTimePosted;
    private UserDTO author;

    /**
     * Init with empty lists.
     */
    public ArticleDisplayDTO() {
        this.topics = new ArrayList<>();
    }
}
