package com.meetup.utils;

import com.meetup.entities.Article;
import com.meetup.entities.Topic;
import com.meetup.entities.dto.ArticleDisplayDTO;
import com.meetup.entities.dto.UserDTO;
import java.util.List;

public class ArticleDTOConverter {

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
