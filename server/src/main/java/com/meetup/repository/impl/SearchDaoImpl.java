package com.meetup.repository.impl;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;
import com.meetup.model.mapper.MeetupMapper;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.ISearchDAO;
import com.meetup.repository.ITopicDAO;
import com.meetup.utils.constants.DbQueryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * implementation of class for working with filters and meetups in DB.
 */
@Repository
@PropertySource("classpath:sql/search_queries.properties")
public class SearchDaoImpl implements ISearchDAO {
    @Autowired
    DataSource dataSource;

    @Autowired
    ITopicDAO topicDAO;
    @Autowired
    private NamedParameterJdbcTemplate template;


    @Value("${get_user_filters_by_id}")
    private String getSavedFiltersForUser;

    @Value("${add_filter}")
    private String addFilter;


    @Value("${search_meetups_by_topic_add}")
    private String searchbyTopic;

    /**
     * SQl function for filtering
     */
    @Value("${search_by_filter_using_function}")
    private String searchMeetupsByFilterWithFunction;

    @Autowired
    IMeetupDAO meetupDAO;

    /**
     * .
     *
     * @param f      Filter to save.
     * @param userId user whose filter it is
     * @return Filter
     */
    @Override
    public Filter saveFilterToCurrentUser(Filter f, int userId) {
        f.setId_user(userId);
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_user", userId)
                .addValue("id_language", f.nullOrIdLanguage())
                .addValue("name", f.getName())
                .addValue("rate_from", f.getRate_from())
                .addValue("rate_to", f.getRate_to())
                .addValue("time_from", f.getTime_from())
                .addValue("time_to", f.getTime_to())
                .addValue("id_topic", f.nullOrIdTopic())
                .addValue("title_substring", f.getTitle_substring());
        template.update(addFilter, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            f.setId(holder.getKey().intValue());

        }
        return f;
    }

    /**
     * .
     *
     * @param userId user whose filters we access
     * @return List of Filters
     */
    @Override
    public List<Filter> getUserFiltersSaved(int userId) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_user_param", userId);
        return this.template
                .query(getSavedFiltersForUser, param,
                        (resultSet, i) -> toFilter(resultSet));
    }


    private Filter toFilter(final ResultSet rs) throws SQLException {
        Filter fil = new Filter();
        fil.setId(rs.getInt("id"));
        fil.setId_user(rs.getInt("id_user"));
        fil.setName(rs.getString("name"));
        fil.setId_language(rs.getInt("id_language"));
        fil.setRate_from(rs.getFloat("rate_from"));
        fil.setRate_to(rs.getFloat("rate_to"));
        fil.setTime_from(rs.getTimestamp("time_from"));
        fil.setTime_to(rs.getTimestamp("time_to"));
        fil.setTitle_substring(rs.getString("title_substring"));
        fil.setTopic_id(rs.getInt("id_topic"));

        fil.setTopic_name(getTopicName(fil.getTopic_id()));
        return fil;
    }

    private String getTopicName(int topic_id) {
        return topicDAO.findTopicByID(topic_id).getName();
    }


    /**
     * @param filter custom Filter from frontend.
     * @return List of matched meetups
     */
    @Override
    public List<Meetup> searchWithFilter(final Filter filter) throws SQLException {
           SqlParameterSource param = new MapSqlParameterSource()
                .addValue(DbQueryConstants.id_language_param.name(), filter.nullOrIdLanguage())
                .addValue(DbQueryConstants.title_param.name(), filter.getTitle_substring())
                .addValue(DbQueryConstants.topic_id_param.name(), filter.nullOrIdTopic())
                .addValue(DbQueryConstants.start_date_param.name(), filter.getTime_from())
                .addValue(DbQueryConstants.end_date_param.name(), filter.getTime_to())
                .addValue(DbQueryConstants.rate_from.name(), filter.nullOrRateFrom())
                .addValue(DbQueryConstants.rate_to.name(), filter.nullOrRateTo());
        List<Meetup> foundmeetups = template.query(searchMeetupsByFilterWithFunction, param, new MeetupMapper());


        return foundmeetups;
    }

}
