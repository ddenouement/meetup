package com.meetup.model.mapper;

import com.meetup.entities.dto.CommentaryDisplayDTO;
import com.meetup.utils.DbQueryConstants;
import com.meetup.utils.TimeUtility;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Class, used to map ResultSet from DB to Commentary model.
 */
public final class CommentaryMapper implements RowMapper<CommentaryDisplayDTO> {

    /**
     * . Callback method of RowMapper interface.
     *
     * @param rs A table of data representing a database result set.
     * @param rowNum the number of the current row
     * @return Meetup object for the current row
     * @throws SQLException Exception due to SQL
     */
    public CommentaryDisplayDTO mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        CommentaryDisplayDTO commentary = new CommentaryDisplayDTO();
        commentary.setId(rs.getInt(DbQueryConstants.id.name()));
        commentary.setAuthorID(rs.getInt(DbQueryConstants.id_author.name()));
        commentary.setAuthorLogin(rs.getString(DbQueryConstants.author_login.name()));
        commentary.setArticleID(rs.getInt(DbQueryConstants.id_article.name()));
        commentary.setContents(rs.getString(DbQueryConstants.contents.name()));
        commentary.setTimePosted(
            TimeUtility.convertToLocalDateTime(
                rs.getTimestamp(DbQueryConstants.time_posted.name())));
        return commentary;
    }
}
