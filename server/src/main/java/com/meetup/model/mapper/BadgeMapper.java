package com.meetup.model.mapper;

import com.meetup.entities.Badge;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.meetup.utils.constants.DbQueryConstants;
import org.springframework.jdbc.core.RowMapper;

/**.
 * custom RowMapper
 */
public final class BadgeMapper implements RowMapper<Badge> {

    /**.
     * Map a row from a result set to an instance of class Language.
     *
     * @param rs result set
     * @param rowNum number of row to map
     * @return Badge created from specified row
     * @throws SQLException if an SQLException is encountered getting column
     * values
     */
    public Badge mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        Badge badge = new Badge();
        badge.setId(rs.getInt(DbQueryConstants.id.name()));
        badge.setName(rs.getString(DbQueryConstants.name.name()));
        badge.setScript(rs.getString(DbQueryConstants.script.name()));
        return badge;
    }
}
