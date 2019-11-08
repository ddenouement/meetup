package com.meetup.model.mapper;

import com.meetup.entities.dto.ComplaintDTO;
import com.meetup.utils.DbQueryConstants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplaintMapper implements RowMapper<ComplaintDTO> {

    /**
     * . Callback method of RowMapper interface.
     *
     * @param rs A table of data representing a database result set.
     * @param rowNum the number of the current row
     * @return Topic object for the current row
     * @throws java.sql.SQLException Exception due to SQL
     */
    public ComplaintDTO mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        ComplaintDTO c = new ComplaintDTO();
        c.setId(rs.getInt(DbQueryConstants.id.name()));
        c.setContent(rs.getString(DbQueryConstants.reason.name()));
        c.setId_user_from(rs.getInt(DbQueryConstants.id_source.name()));
        c.setId_user_to(rs.getInt(DbQueryConstants.id_destination.name()));
        // TODO: do we need to get timestamp there?
        c.setPostedDate(rs.getDate(DbQueryConstants.time_posted.name()));
        c.setRead(rs.getBoolean(DbQueryConstants.read.name()));
        return c;
    }
}
