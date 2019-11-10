package com.meetup.repository;

import com.meetup.entities.Article;
import com.meetup.entities.Commentary;
import com.meetup.entities.Topic;
import com.meetup.entities.dto.ArticleCreationDTO;
import com.meetup.entities.dto.CommentaryDisplayDTO;
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
     * Count the number of articles in database.
     *
     * @return int number of all articles
     */
    int getAllArticlesCount();

    /**
     * Return all articles with display using pagination parameters.
     * @param limit limit for paging
     * @param offset offset for paging
     * @return List of articles by pages
     */
    List<Article> getAllArticlesByPages(int limit, int offset);

    /**
     * Get commentaries of specific article.
     *
     * @param articleID Article ID.
     * @return List of commentaries.
     */
    List<CommentaryDisplayDTO> getArticleCommentaries(int articleID);

    /**
     * Insert commentary into DB.
     *
     * @param articleID Article ID.
     * @param userID Author ID.
     * @param commentary Commentary.
     */
    Commentary addCommentary(int articleID, int userID, Commentary commentary);

    /**
     * Remove commentary from DB.
     * @param commentID
     * Commentary ID.
     */
    void removeCommentary(int commentID);
}
