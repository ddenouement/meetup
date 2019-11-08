package com.meetup.utils;

import lombok.Data;

import java.util.Map;

/**
 * Custom  data holder class.
 */
@Data
public class SqlAndParamsHolder {

    /**
     * Sql string.
     */
    private String sql;
    /**
     * Parameters map for query.
     */
    private Map<String, ?> params;

    /**
     * Construct data holder.
     *
     * @param first sql query
     * @param second parameters needed
     */
    public SqlAndParamsHolder(final String first, final Map second) {
        this.sql = first;
        this.params = second;
    }
}
