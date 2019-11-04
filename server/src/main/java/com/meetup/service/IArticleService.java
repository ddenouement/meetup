package com.meetup.service;

import com.meetup.entities.Commentary;
import com.meetup.entities.dto.ArticleCreationDTO;
import com.meetup.entities.dto.ArticleDisplayDTO;
import com.meetup.entities.dto.CommentaryDisplayDTO;
import java.util.List;

/**
 * Article service interface. Used to manage article functionality.
 */
public interface IArticleService {

    /**
     * Create, and post article.
     *
     * @param articleCreationDTO Article, that should be posted
     * @param userLogin User login, that posts an article.
     */
    void postArticle(ArticleCreationDTO articleCreationDTO,
        String userLogin);

    /**
     * Remove article by speaker.
     *
     * @param articleID Article ID, that should be removed
     * @param userLogin User login, that removes an article.
     */
    void removeArticle(int articleID, String userLogin);

    /**
     * Get article, that could be displayed.
     *
     * @param articleID Article ID.
     * @return ArticleDisplayDTO.
     */
    ArticleDisplayDTO getDisplayableArticle(int articleID);

    /**
     * Get all displayable articles.
     *
     * @return List of articles.
     */
    List<ArticleDisplayDTO> getAllDisplayableArticles();

    /**
     * Remove article by admin.
     *
     * @param articleID Article ID.
     */
    void removeArticleByAdmin(int articleID);

    /**
     * Get commentaries of specific article.
     *
     * @param articleID Article ID.
     * @return List of commentaries.
     */
    List<CommentaryDisplayDTO> getCommentaries(int articleID);

    /**
     * Post commentary on article.
     *
     * @param articleID Article ID.
     * @param userLogin User login.
     * @param commentary Commentary.
     */
    void postCommentary(int articleID, String userLogin, Commentary commentary);
}
