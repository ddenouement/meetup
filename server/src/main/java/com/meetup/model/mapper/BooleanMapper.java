package com.meetup.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public final class BooleanMapper implements RowMapper<Boolean> {

    /**.
     * Map a row from a result set to an instance of class String.
     *
     * @param rs result set
     * @param rowNum number of row to map
     * @return Boolean created from specified row
     * @throws SQLException if an SQLException is encountered getting column
     * values
     */
    @Override
    public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getBoolean(1);
    }
}
