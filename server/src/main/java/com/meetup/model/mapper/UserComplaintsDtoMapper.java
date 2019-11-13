package com.meetup.model.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.meetup.utils.constants.DbQueryConstants;

import com.meetup.entities.dto.UserComplaintsDTO;
import org.springframework.jdbc.core.RowMapper;

public class UserComplaintsDtoMapper implements RowMapper<UserComplaintsDTO> {
    /**.
     * Callback method of RowMapper interface.
     * @param rs
     * A table of data representing a database result set.
     * @param rowNum
     * the number of the current row
     * @return
     * Topic object for the current row
     * @throws SQLException
     * Exception due to SQL
     */
    @Override
    public UserComplaintsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserComplaintsDTO userComplaints = new UserComplaintsDTO();
        userComplaints.setId(rs.getInt(DbQueryConstants.id.name()));
        userComplaints.setLogin(rs.getString(DbQueryConstants.login.name()));
        userComplaints.setEmail(rs.getString(DbQueryConstants.email.name()));
        userComplaints.setFirstName(rs.getString(DbQueryConstants.first_name.name()));
        userComplaints.setLastName(rs.getString(DbQueryConstants.last_name.name()));
        userComplaints.setActive(rs.getBoolean(DbQueryConstants.active.name()));
        userComplaints.setComplaintsCount(rs.getInt(DbQueryConstants.complaints_count.name()));
        return userComplaints;
    }
}
