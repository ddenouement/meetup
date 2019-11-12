package com.meetup.model.mapper;

import com.meetup.utils.constants.DbQueryConstants;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**.
 * custom RowMapper
 */
public final class CountMapper implements RowMapper<Integer> {

    /**.
     * Map a row from a result set to an instance of class Integer.
     *
     * @param rs result set
     * @param rowNum number of row to map
     * @return Integer created from specified row
     * @throws SQLException if an SQLException is encountered getting column
     * values
     */
    public Integer mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        return rs.getInt(DbQueryConstants.count.name());
    }
}
