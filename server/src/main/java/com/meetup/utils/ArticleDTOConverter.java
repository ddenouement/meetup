package com.meetup.utils;

import com.meetup.entities.Article;
import com.meetup.entities.Topic;
import com.meetup.entities.dto.ArticleDisplayDTO;
import com.meetup.entities.dto.UserDTO;
import java.util.List;

/**
 * Utility class used to convert Article object
 * to displayable DTO.
 */
public class ArticleDTOConverter {

    /**
     * Convert Article object to displayable DTO.
     * @param article
     * Article.
     * @param topics
     * Topics of article;
     * @param userDTO
     * User DTO.
     * @return
     * ArticleDisplayDTO.
     */
    public ArticleDisplayDTO convertToArticleDisplayDTO(final Article article,
        final List<Topic> topics,
        final UserDTO userDTO) {
        ArticleDisplayDTO articleDisplayDTO = new ArticleDisplayDTO();
        articleDisplayDTO.setAuthor(userDTO);
        articleDisplayDTO.setTitle(article.getTitle());
        articleDisplayDTO.setContent(article.getContent());
        articleDisplayDTO
            .setDateTimePosted(article.getPostDateTime().toString());
        articleDisplayDTO.setTopics(topics);
        return articleDisplayDTO;
    }

}
