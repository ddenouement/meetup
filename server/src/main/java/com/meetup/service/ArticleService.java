package com.meetup.service;

import com.meetup.entities.dto.ArticleCreationDTO;

/**
 * Article service interface.
 * Used to manage article functionality.
 */
public interface ArticleService {

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
}
