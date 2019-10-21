package com.meetup.repository;

import com.meetup.entities.dto.ArticleCreationDTO;

/**
 * Interface for Article repository (DAO).
 */
public interface IArticleDAO {

    /**
     * Insert new Article into DB.
     * @param articleCreationDTO
     * Article, that should be inserted.
     */
    void insertNewArticle(ArticleCreationDTO articleCreationDTO,
        int authorId);

    /**.
     * Add topic to article in DB.
     * @param articleID
     * Article, that should have topic
     * @param topicID
     * Topic to be added to article.
     */
    void addTopicToArticle(final int articleID, final int topicID);

}
