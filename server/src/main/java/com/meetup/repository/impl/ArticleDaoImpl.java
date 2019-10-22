package com.meetup.repository.impl;

import com.meetup.entities.Article;
import com.meetup.entities.dto.ArticleCreationDTO;
import com.meetup.model.mapper.ArticleMapper;
import com.meetup.repository.IArticleDAO;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Article repository class.
 * Used to communicate with database, to perform operations with Articles.
 */
@Repository
@PropertySource("classpath:sql/article_queries.properties")
public class ArticleDaoImpl implements IArticleDAO {

    /**
     * JDBC template.
     */
    @Autowired
    private NamedParameterJdbcTemplate template;
    /**
     * SQL reference script.
     * Insert new Article.
     */
    @Value("${insert_new_article}")
    private String insertNewArticle;
    /**
     * SQL reference script.
     * Add topic to article.
     */
    @Value("${add_topic_to_article}")
    private String addTopicToArticle;
    /**
     * SQL reference script.
     * Get article by ID.
     */
    @Value("${find_article_by_id}")
    private String findArticleByID;
    /**
     * SQL reference script.
     * Get article by ID.
     */
    @Value("${remove_article}")
    private String removeArticle;

    /**
     * Insert new Article into DB.
     * @param articleCreationDTO
     * Article, that should be inserted.
     * @param authorId
     * Author ID, that creates an article.
     */
    @Override
    public void insertNewArticle(final ArticleCreationDTO articleCreationDTO,
        final int authorId) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id_author", authorId)
            .addValue("id_title", articleCreationDTO.getTitle())
            .addValue("contents", articleCreationDTO.getContent())
            .addValue("time_posted", getCurrentTimestamp());
        template.update(insertNewArticle, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            int articleID = holder.getKey().intValue();
            //adding topics to DB
            //TODOo rewrite
            for (int topicID : articleCreationDTO.getTopicIds()) {
                addTopicToArticle(articleID, topicID);
            }
        }
    }

    /**
     * Add topic to article in DB.
     * @param articleID
     * Article, that should have topic
     * @param topicID
     * Topic to be added to article.
     */
    @Override
    public void addTopicToArticle(final int articleID, final int topicID) {
        Map parametersForAddingTopic = new HashMap();
        parametersForAddingTopic.put("id_article", articleID);
        parametersForAddingTopic.put("id_topic", topicID);
        template.update(addTopicToArticle, parametersForAddingTopic);
    }

    /**
     * Find article by ID.
     * @param articleID
     * Article ID.
     * @return
     * Article.
     */
    @Override
    public Article findArticleByID(final int articleID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id", articleID);
        return this.template
            .queryForObject(findArticleByID, param, new ArticleMapper());
    }

    /**
     * Remove article by ID.
     * @param articleID
     * Article ID.
     */
    @Override
    public void removeArticle(final int articleID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id", articleID);
        template.update(removeArticle, param);
    }

    /**
     * Get current date.
     * @return
     * SQL Timestamp date format.
     */
    public Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }
}