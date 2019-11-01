package com.meetup.model.mapper;

import com.meetup.entities.Topic;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.meetup.utils.DbQueryConstants;
import org.springframework.jdbc.core.RowMapper;

/**.
 * Class, used to map ResultSet from DB to Topic model.
 */
public final class TopicMapper implements RowMapper<Topic> {

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
    public Topic mapRow(final ResultSet rs, final int rowNum)
        throws SQLException {
        Topic topic = new Topic();
        topic.setId(rs.getInt(DbQueryConstants.id.name()));
        topic.setName(rs.getString(DbQueryConstants.name.name()));
        return topic;
    }
}
