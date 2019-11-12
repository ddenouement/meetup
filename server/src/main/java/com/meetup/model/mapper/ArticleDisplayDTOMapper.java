package com.meetup.model.mapper;

import com.meetup.entities.dto.ArticleDisplayDTO;
import com.meetup.entities.dto.SimpleUserDTO;
import com.meetup.utils.TimeUtility;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.meetup.utils.constants.DbQueryConstants;
import org.springframework.jdbc.core.RowMapper;

/**
 *  Class, used to map ResultSet from DB to ArticleDisplayDTO model.
 */
public final class ArticleDisplayDTOMapper implements RowMapper<ArticleDisplayDTO> {

    /**
     * Callback method of RowMapper interface.
     *
     * @param rs A table of data representing a database result set.
     * @param rowNum the number of the current row
     * @return Meetup object for the current row
     * @throws SQLException Exception due to SQL
     */
    public ArticleDisplayDTO mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        ArticleDisplayDTO article = new ArticleDisplayDTO();
        SimpleUserDTO speaker = new SimpleUserDTO();
        speaker.setId(rs.getInt(DbQueryConstants.id_author.name()));
        speaker.setLogin(rs.getString(DbQueryConstants.speaker_login.name()));
        speaker.setFirstName(rs.getString(DbQueryConstants.speaker_first_name.name()));
        speaker.setLastName(rs.getString(DbQueryConstants.speaker_last_name.name()));
        speaker.setRate(rs.getFloat(DbQueryConstants.speaker_rate.name()));
        speaker.setNumRates(rs.getInt(DbQueryConstants.speaker_num_rates.name()));
        article.setId(rs.getInt(DbQueryConstants.id.name()));
        article.setTitle(rs.getString(DbQueryConstants.id_title.name()));
        article.setContent(rs.getString(DbQueryConstants.contents.name()));
        article.setDateTimePosted(
            TimeUtility.convertToLocalDateTime(rs.getTimestamp(DbQueryConstants.time_posted.name())).toString());
        article.setAuthor(speaker);
        return article;
    }
}
