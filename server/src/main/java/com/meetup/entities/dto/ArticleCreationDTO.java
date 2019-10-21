package com.meetup.entities.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Class to transfer data about article on creation.
 */
@Data
public class ArticleCreationDTO {

    /**
     * Title of article.
     */
    private String title;
    /**
     * Content of article.
     */
    private String content;
    /**
     * ID's of topics of this article.
     */
    private List<Integer> topicIds;

    /** Init with empty lists. */
    public ArticleCreationDTO() {
        this.topicIds = new ArrayList<>();
    }
}
