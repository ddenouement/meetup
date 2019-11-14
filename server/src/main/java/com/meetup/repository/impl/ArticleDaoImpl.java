package com.meetup.repository.impl;

import com.meetup.entities.Commentary;
import com.meetup.entities.Topic;
import com.meetup.entities.dto.ArticleCreationDTO;
import com.meetup.entities.dto.ArticleDisplayDTO;
import com.meetup.entities.dto.CommentaryDisplayDTO;
import com.meetup.model.mapper.ArticleDisplayDTOMapper;
import com.meetup.model.mapper.CommentaryDisplayDTOMapper;
import com.meetup.model.mapper.IntegerMapper;
import com.meetup.model.mapper.TopicMapper;
import com.meetup.repository.IArticleDAO;

import static com.meetup.utils.constants.DbQueryConstants.*;

import com.meetup.utils.TimeUtility;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meetup.utils.constants.DbQueryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


/**
 * Article repository class. Used to communicate with database, to perform
 * operations with Articles.
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
     * SQL reference script. Insert new Article.
     */
    @Value("${insert_new_article}")
    private String insertNewArticle;
    /**
     * SQL reference script. Add topic to article.
     */
    @Value("${add_topic_to_article}")
    private String addTopicToArticle;
    /**
     * SQL reference script. Get article by ID.
     */
    @Value("${find_article_by_id}")
    private String findArticleByID;
    /**
     * SQL reference script. Get article topics.
     */
    @Value("${get_article_topics}")
    private String findArticleTopics;
    /**
     * SQL reference script. Remove article by ID.
     */
    @Value("${remove_article}")
    private String removeArticle;
    /**
     * SQL reference script. Get all articles.
     */
    @Value("${find_all_articles}")
    private String findAllArticles;
    /**
     * SQL reference script. Get articles by pages.
     */
    @Value("${find_all_articles_by_pages}")
    private String findAllArticlesByPages;
    /**
     * SQL reference script. Count all articles.
     */
    @Value("${find_all_articles_count}")
    private String findAllArticlesCount;
    /**
     * SQL reference script. Find article commentaries.
     */
    @Value("${find_article_commentaries}")
    private String findArticleCommentaries;
    /**
     * SQL reference script. Insert new commentary.
     */
    @Value("${insert_new_commentary}")
    private String insertNewCommentary;
    /**
     * SQL reference script. Insert new commentary.
     */
    @Value("${remove_commentary}")
    private String removeCommentary;


    /**
     * Insert new Article into DB.
     *
     * @param articleCreationDTO Article, that should be inserted.
     * @param authorId Author ID, that creates an article.
     */
    @Override
    public void insertNewArticle(final ArticleCreationDTO articleCreationDTO,
        final int authorId) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(id_author.name(), authorId)
            .addValue(id_title.name(), articleCreationDTO.getTitle())
            .addValue(contents.name(), articleCreationDTO.getContent())
            .addValue(time_posted.name(), getCurrentTimestamp());
        template.update(insertNewArticle, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            int articleID = holder.getKey().intValue();
            for (int topicID : articleCreationDTO.getTopicIds()) {
                addTopicToArticle(articleID, topicID);
            }
        }
    }

    /**
     * Add topic to article in DB.
     *
     * @param articleID Article, that should have topic
     * @param topicID Topic to be added to article.
     */
    @Override
    public void addTopicToArticle(final int articleID, final int topicID) {
        Map parametersForAddingTopic = new HashMap();
        parametersForAddingTopic.put(id_article.name(), articleID);
        parametersForAddingTopic.put(id_topic.name(), topicID);
        template.update(addTopicToArticle, parametersForAddingTopic);
    }

    /**
     * Find article by ID.
     *
     * @param articleID Article ID.
     * @return Article.
     */
    @Override
    public ArticleDisplayDTO findArticleByID(final int articleID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(id.name(), articleID);
        try {
            return this.template
                .queryForObject(findArticleByID, param,
                    new ArticleDisplayDTOMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    /**
     * Get topics of article.
     *
     * @param articleID Article ID.
     * @return List of topics.
     */
    @Override
    public List<Topic> getArticleTopics(final int articleID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id_article", articleID);
        return template.query(findArticleTopics, param, new TopicMapper());
    }

    /**
     * Remove article by ID.
     *
     * @param articleID Article ID.
     */
    @Override
    public void removeArticle(final int articleID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(id.name(), articleID);
        template.update(removeArticle, param);
    }

    /**
     * Get all articles.
     *
     * @return List of articles.
     */
    @Override
    public List<ArticleDisplayDTO> getAllArticles() {
        return this.template
            .query(findAllArticles, new ArticleDisplayDTOMapper());
    }

    @Override
    public int getAllArticlesCount() {
        List<Integer> res = this.template
            .query(findAllArticlesCount, new IntegerMapper());
        if (res.isEmpty()) {
            return 0;
        } else {
            return res.get(0);
        }
    }

    @Override
    public List<ArticleDisplayDTO> getAllArticlesByPages(int limit,
        int offset) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.limit.name(), limit)
            .addValue(DbQueryConstants.offset.name(), offset);
        return this.template
            .query(findAllArticlesByPages, param,
                new ArticleDisplayDTOMapper());
    }

    @Override
    public List<CommentaryDisplayDTO> getArticleCommentaries(
        final int articleID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(id_article.name(), articleID);
        return this.template
            .query(findArticleCommentaries, param,
                new CommentaryDisplayDTOMapper());
    }

    /**
     * Insert commentary to DB.
     *
     * @param articleID Article ID.
     * @param authorID Author ID.
     * @param commentary Commentary.
     */
    @Override
    public Commentary addCommentary(final int articleID, final int authorID,
        Commentary commentary) {
        commentary.setAuthorID(authorID);
        commentary.setArticleID(articleID);
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(id_author.name(), authorID)
            .addValue(id_article.name(), articleID)
            .addValue(contents.name(), commentary.getContents())
            .addValue(time_posted.name(), Timestamp.valueOf(commentary.getTimePosted()));
        template.update(insertNewCommentary, param);
        return commentary;
    }

    /**
     * Remove commentary from DB.
     *
     * @param commentID Commentary ID.
     */
    @Override
    public void removeCommentary(final int commentID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(id.name(), commentID);
        template.update(removeCommentary, param);
    }

    /**
     * Get current date.
     *
     * @return SQL Timestamp date format.
     */
    public Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }
}
