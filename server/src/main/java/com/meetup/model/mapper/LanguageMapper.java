package com.meetup.model.mapper;

import com.meetup.entities.Language;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**.
 * custom RowMapper
 */
public final class LanguageMapper implements RowMapper<Language> {

    /**.
     * Map a row from a result set to an instance of class Language.
     *
     * @param rs result set
     * @param rowNum number of row to map
     * @return Language created from specified row
     * @throws SQLException if an SQLException is encountered getting column
     * values
     */
    public Language mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        Language language = new Language();
        language.setId(rs.getInt("id"));
        language.setName(rs.getString("name"));
        System.out.println("+");
        return language;
    }
}
