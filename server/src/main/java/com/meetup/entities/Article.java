package com.meetup.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Article class.
 */
@Data
public class Article {

    /**
     * ID of article.
     */
    private int id;
    /**
     * ID of author.
     */
    private int authorID;
    /**
     * Title of article.
     */
    private String title;
    /**
     * Content of article.
     */
    private String content;
    /**
     * Date of posting of article.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime postDate;
    /**
     * ID's of topics of this article.
     */
    private List<Integer> topicIds;

    /** Init with empty lists. */
    public Article() {
        this.topicIds = new ArrayList<>();
    }
}
