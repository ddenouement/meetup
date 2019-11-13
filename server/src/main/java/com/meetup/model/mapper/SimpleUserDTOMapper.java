package com.meetup.model.mapper;

import com.meetup.entities.dto.SimpleUserDTO;
import com.meetup.utils.constants.DbQueryConstants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
/**.
 * Class, used to map ResultSet from DB to SimpleUserDTO model.
 */
public final class SimpleUserDTOMapper implements RowMapper<SimpleUserDTO> {
    /**.
     * Callback method of RowMapper interface.
     * @param rs
     * A table of data representing a database result set.
     * @param rowNum
     * the number of the current row
     * @return
     * User object for the current row
     * @throws SQLException
     * Exception due to SQL
     */
    @Override
    public SimpleUserDTO mapRow(final ResultSet rs, final int rowNum)
            throws SQLException {
        SimpleUserDTO user = new SimpleUserDTO();
        user.setId(rs.getInt(DbQueryConstants.id.name()));
        user.setLogin(rs.getString(DbQueryConstants.login.name()));
        user.setFirstName(rs.getString(DbQueryConstants.first_name.name()));
        user.setLastName(rs.getString(DbQueryConstants.last_name.name()));
        user.setRate(rs.getFloat(DbQueryConstants.rate.name()));
        user.setNumRates(rs.getInt(DbQueryConstants.num_rates.name()));
        return user;
    }
}
