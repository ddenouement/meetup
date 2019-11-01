package com.meetup.model.mapper;

import com.meetup.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.meetup.utils.DbQueryConstants;
import org.springframework.jdbc.core.RowMapper;

/**.
 * Class, used to map ResultSet from DB to User model.
 */
public final class UserMapper implements RowMapper<User> {

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
    public User mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        User user = new User();
        user.setId(rs.getInt(DbQueryConstants.id.name()));
        user.setLogin(rs.getString(DbQueryConstants.login.name()));
        user.setEmail(rs.getString(DbQueryConstants.email.name()));
        user.setPassword(rs.getString(DbQueryConstants.password.name()));
        user.setFirstName(rs.getString(DbQueryConstants.first_name.name()));
        user.setLastName(rs.getString(DbQueryConstants.last_name.name()));
        user.setActive(rs.getBoolean(DbQueryConstants.active.name()));
        user.setAbout(rs.getString(DbQueryConstants.about.name()));
        user.setRate(rs.getFloat(DbQueryConstants.rate.name()));
        user.setNumRates(rs.getInt(DbQueryConstants.num_rates.name()));
        return user;
    }
}
