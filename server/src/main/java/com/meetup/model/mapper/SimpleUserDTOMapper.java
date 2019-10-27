package com.meetup.model.mapper;

import com.meetup.entities.dto.SimpleUserDTO;
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
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setRate(rs.getFloat("rate"));
        user.setNumRates(rs.getInt("num_rates"));
        return user;
    }
}
