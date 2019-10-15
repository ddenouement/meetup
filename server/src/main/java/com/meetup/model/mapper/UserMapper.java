package com.meetup.model.mapper;

import com.meetup.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Class, used to map ResultSet from DB to User model.
 */
public final class UserMapper implements RowMapper<User> {

    /**
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
    public User mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setActive(rs.getBoolean("active"));
        user.setAbout(rs.getString("about"));
        user.setRate(rs.getFloat("rate"));
        return user;
    }
}
