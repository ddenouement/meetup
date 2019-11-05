package com.meetup.repository;

import com.meetup.entities.Article;
import com.meetup.entities.Commentary;
import com.meetup.entities.Topic;
import com.meetup.entities.dto.ArticleCreationDTO;
import java.util.List;

/**
 * Interface for Article repository (DAO).
 */
public interface IArticleDAO {

    /**
     * Insert new Article into DB.
     *
     * @param articleCreationDTO Article, that should be inserted.
     * @param authorId Author id, that creates an article.
     */
    void insertNewArticle(ArticleCreationDTO articleCreationDTO,
        int authorId);

    /**
     * . Add topic to article in DB.
     *
     * @param articleID Article, that should have topic
     * @param topicID Topic to be added to article.
     */
    void addTopicToArticle(int articleID, int topicID);


    /**
     * Find article by ID.
     *
     * @param articleID Article ID.
     * @return Article object.
     */
    Article findArticleByID(int articleID);

    /**
     * Get topics of article.
     *
     * @param articleID Article ID.
     * @return List of topics.
     */
    List<Topic> getArticleTopics(int articleID);

    /**
     * Remove article by ID.
     *
     * @param articleID Article ID.
     */
    void removeArticle(int articleID);

    /**
     * Get all articles.
     *
     * @return List of articles.
     */
    List<Article> getAllArticles();

    /**
     * Get commentaries of specific article.
     *
     * @param articleID Article ID.
     * @return List of commentaries.
     */
    List<Commentary> getArticleCommentaries(int articleID);

    /**
     * Insert commentary into DB.
     *
     * @param articleID Article ID.
     * @param userID Author ID.
     * @param commentary Commentary.
     */
    void addCommentary(int articleID, int userID, Commentary commentary);
}
