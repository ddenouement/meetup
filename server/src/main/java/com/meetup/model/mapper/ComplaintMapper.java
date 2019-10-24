package com.meetup.model.mapper;

import com.meetup.entities.dto.ComplaintDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplaintMapper implements RowMapper<ComplaintDTO> {

/**.
 * Callback method of RowMapper interface.
 * @param rs
 * A table of data representing a database result set.
 * @param rowNum
 * the number of the current row
 * @return
 * Topic object for the current row
 * @throws java.sql.SQLException
 * Exception due to SQL
 */
public ComplaintDTO mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
    ComplaintDTO c = new ComplaintDTO();
        c.setId(rs.getInt("id"));
        c.setContent(rs.getString("reason"));
    c.setId_user_from(rs.getInt("id_source"));
    c.setId_user_to(rs.getInt("id_destination"));
    c.setPostedDate(rs.getDate("time_posted"));
        return c;
        }
}
