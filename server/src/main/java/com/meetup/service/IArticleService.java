package com.meetup.service;

import com.meetup.entities.dto.ArticleCreationDTO;
import com.meetup.entities.dto.ArticleDisplayDTO;

/**
 * Article service interface.
 * Used to manage article functionality.
 */
public interface IArticleService {

    /**
     * Create, and post article.
     * @param articleCreationDTO
     * Article, that should be posted
     * @param userLogin
     * User login, that posts an article.
     *
     */
    void postArticle(ArticleCreationDTO articleCreationDTO,
        String userLogin);

    /**
     * Remove article by speaker.
     * @param articleID Article ID, that should be removed
     * @param userLogin
     * User login, that removes an article.
     *
     */
    void removeArticle(int articleID, String userLogin);

    /**
     * Get article, that could be displayed.
     * @return
     * ArticleDisplayDTO.
     */
    ArticleDisplayDTO getDisplayableArticle();
}
