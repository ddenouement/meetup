package com.meetup.repository.impl;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;
import com.meetup.entities.dto.MeetupDisplayDTO;
import com.meetup.model.mapper.MeetupDisplayDtoMapper;
import com.meetup.model.mapper.MeetupMapper;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.ISearchDAO;
import com.meetup.repository.ITopicDAO;
import com.meetup.utils.constants.DbQueryConstants;
import lombok.Builder;
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
 * implementation of class for working with filters in DB.
 */
@Repository
@PropertySource("classpath:sql/search_queries.properties")
public class SearchDaoImpl implements ISearchDAO {


    private ITopicDAO topicDAO;
    private NamedParameterJdbcTemplate template;

    /**.
     * SQL query for retrieving saved filters of given user
     */
    @Value("${get_user_filters_by_id}")
    private String getSavedFiltersForUser;
    /**.
     * SQL query  for saving filter to a given user
     */
    @Value("${add_filter}")
    private String addFilter;

    /**
     * SQl function for filtering
     */
    @Value("${search_by_filter_using_function}")
    private String searchMeetupsByFilterWithFunction;

    @Autowired
    SearchDaoImpl( final ITopicDAO topicDAO,
                  final NamedParameterJdbcTemplate parameterJdbcTemplate){
        this.template = parameterJdbcTemplate;
        this.topicDAO=topicDAO;

    }


    /**
     * @param f      Filter to save.
     * @param userId user whose filter it is
     * @return Filter
     */
    @Override
    public Filter saveFilter(Filter f, int userId) {
        f.setId_user(userId);
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue(DbQueryConstants.id_user.name(), userId)
                .addValue(DbQueryConstants.id_language.name(), f.nullOrIdLanguage())
                .addValue(DbQueryConstants.name.name(), f.getName())
                .addValue(DbQueryConstants.rate_from.name(), f.getRate_from())
                .addValue(DbQueryConstants.rate_to.name(), f.getRate_to())
                .addValue(DbQueryConstants.time_from.name(), f.getTime_from())
                .addValue(DbQueryConstants.time_to.name(), f.getTime_to())
                .addValue(DbQueryConstants.id_topic.name(), f.nullOrIdTopic())
                .addValue(DbQueryConstants.title_substring.name(), f.getTitle_substring());
        template.update(addFilter, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            f.setId(holder.getKey().intValue());

        }
        return f;
    }

    /**Get saved filters of a given user.
     * @param userId user whose filters we access
     * @return List of Filters
     */
    @Override
    public List<Filter> getFilters(int userId) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue(DbQueryConstants.id_user_param.name(), userId);
        return  this.template
                .query(getSavedFiltersForUser, param,
                        (resultSet, i) -> toFilter(resultSet));
    }

    private Filter toFilter(final ResultSet rs) throws SQLException {
        Filter filter = new Filter();
        filter.setId(rs.getInt(DbQueryConstants.id.name()));
        filter.setId_user(rs.getInt(DbQueryConstants.id_user.name()));
        filter.setName(rs.getString(DbQueryConstants.name.name()));
        filter.setId_language(rs.getInt(DbQueryConstants.id_language.name()));
        filter.setRate_from(rs.getFloat(DbQueryConstants.rate_from.name()));
        filter.setRate_to(rs.getFloat(DbQueryConstants.rate_to.name()));
        filter.setTime_from(rs.getTimestamp(DbQueryConstants.time_from.name()));
        filter.setTime_to(rs.getTimestamp(DbQueryConstants.time_to.name()));
        filter.setTitle_substring(rs.getString(DbQueryConstants.title_substring.name()));
        filter.setTopic_id(rs.getInt(DbQueryConstants.id_topic.name()));

        if (filter.getTopic_id() != 0) {
            filter.setTopic_name(getTopicName(filter.getTopic_id()));
        }
        return filter;
    }


    private String getTopicName(int topicId) {
        return topicDAO.findTopicByID(topicId).getName();
    }

    @Override
    public int getAllMeetupsCount(Filter filter){
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue(DbQueryConstants.id_language_param.name(), filter.nullOrIdLanguage())
                .addValue(DbQueryConstants.title_param.name(), filter.getTitle_substring())
                .addValue(DbQueryConstants.topic_id_param.name(), filter.nullOrIdTopic())
                .addValue(DbQueryConstants.start_date_param.name(), filter.getTime_from())
                .addValue(DbQueryConstants.end_date_param.name(), filter.getTime_to())
                .addValue(DbQueryConstants.rate_from.name(), filter.nullOrRateFrom())
                .addValue(DbQueryConstants.rate_to.name(), filter.nullOrRateTo());

        return template.query(searchMeetupsByFilterWithFunction, param, new MeetupDisplayDtoMapper()).size();
    }


    /**
     * Perform filtered search of meetups
     * @param filter custom Filter from frontsend.
     * @return List of matched meetups
     */
    @Override
    public List<MeetupDisplayDTO> getMeetups(final Filter filter, final Integer offset,
                                             final Integer limit) {

           SqlParameterSource param = new MapSqlParameterSource()
                .addValue(DbQueryConstants.id_language_param.name(), filter.nullOrIdLanguage())
                .addValue(DbQueryConstants.title_param.name(), filter.getTitle_substring())
                .addValue(DbQueryConstants.topic_id_param.name(), filter.nullOrIdTopic())
                .addValue(DbQueryConstants.start_date_param.name(), filter.getTime_from())
                .addValue(DbQueryConstants.end_date_param.name(), filter.getTime_to())
                .addValue(DbQueryConstants.rate_from.name(), filter.nullOrRateFrom())
                .addValue(DbQueryConstants.rate_to.name(), filter.nullOrRateTo())
                   .addValue(DbQueryConstants.offset.name(), offset)
                   .addValue(DbQueryConstants.limit.name(), limit);

        return template.query(searchMeetupsByFilterWithFunction, param, new MeetupDisplayDtoMapper());
    }

}
