package com.meetup.model.mapper;

import com.meetup.entities.Article;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * . Class, used to map ResultSet from DB to Article model.
 */
public final class ArticleMapper implements RowMapper<Article> {

    /**
     * . Callback method of RowMapper interface.
     *
     * @param rs A table of data representing a database result set.
     * @param rowNum the number of the current row
     * @return Meetup object for the current row
     * @throws SQLException Exception due to SQL
     */
    public Article mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setAuthorID(rs.getInt("id_author"));
        article.setTitle(rs.getString("id_title"));
        article.setContent(rs.getString("contents"));
        article.setPostDate(
            TimeUtility.convertToLocalDateTime(rs.getTimestamp("time_posted")));
        return article;
    }
}
