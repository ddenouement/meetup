package com.meetup.repository.impl;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;
import com.meetup.entities.Topic;
import com.meetup.model.mapper.MeetupMapper;
import com.meetup.model.mapper.TopicMapper;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.ISearchDAO;
import com.meetup.utils.SqlAndParamsHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * implementation of class for working with filters and meetups in DB.
 */
@Repository
@PropertySource("classpath:sql/search_queries.properties")
public class SearchDaoImpl implements ISearchDAO {
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Value("${search_meetup_begin}")
    private String beginSql;

    @Value("${search_meetup_substring_title_add}")
    private String titleSearchSql;

    @Value("${search_meetup_in_dates_range_add}")
    private String datesRangeSql;

    @Value("${find_meetups_ids_by_topics}")
    private String topicsListSql;

    @Value("${search_meetup_by_language_add}")
    private String byLanguageSql;

    @Value("${search_meetup_speaker_rate_range_add}")
    private String byRateRangeSql;

    @Value("${search_meetup_speaker_rate_range_first_null_add}")
    private String byRateRangeFirstNullSql;


    @Value("${search_only_scheduled_booked_meetups}")
    private String onlyScheduledOrBookedSql;

    @Value("${search_meetup_date_from_add}")
    private String dateFromSql;

    @Value("${search_meetup_date_to_add}")
    private String dateToSql;

    @Value("${get_user_filters_by_id}")
    private String getSavedFiltersForUser;

    @Value("${get_filter_topics_by_id}")
    private String getFilterTopics;

    @Value("${add_filter}")
    private String addFilter;

    @Value("${add_topic_to_filter}")
    private String addTopicToFilter;

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
                .addValue("id_user",  userId)
                .addValue("id_language", f.getId_language())
                .addValue("name", f.getName())
                .addValue("rate_from", f.getRate_from())
                .addValue("rate_to", f.getRate_to())
                .addValue("time_from", f.getTime_from())
                .addValue("time_to", f.getTime_to())
                .addValue("title_substring", f.getTitle_substring());
        template.update(addFilter, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            f.setId(holder.getKey().intValue());
            for (int topic_id : f.getTopics_ids()) {
                addTopicToFilter(f, topic_id);
            }
        }
        return f;
    }

    private void addTopicToFilter(Filter f, int topic_id) {
        Map namedParameters = new HashMap();
        namedParameters.put("id_filter", f.getId());
        namedParameters.put("id_topic", topic_id);
        template.update(addTopicToFilter, namedParameters);
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

        fil.setTopics(getFilterTopics(fil.getId()));
        fillFilterTopicsIds(fil);
        return fil;
    }

    private void fillFilterTopicsIds(Filter fil) {
        for (Topic topic : fil.getTopics()) {
            fil.getTopics_ids().add(topic.getId());
        }
    }

    private List<Topic> getFilterTopics(int id) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_filter_param", id);

        ResultSet rs = null;
        return
                template.query(getFilterTopics, param, new TopicMapper());
    }

    /**
     * @param filter custom Filter from frontend.
     * @return List of matched meetups
     */
    @Override
    public List<Meetup> searchWithFilter(final Filter filter) {
        SqlAndParamsHolder values = constructValuesAndSqlFromFilter(filter);
        SqlParameterSource param = new MapSqlParameterSource()
                .addValues(values.getParams());

        List<Meetup> foundmeetups =
                template.query(values.getSql(), param, new MeetupMapper());

        for (Meetup m : foundmeetups) {
            m.setTopics(meetupDAO.getMeetupTopics(m.getId()));

        }
        return foundmeetups;
    }

    public SqlAndParamsHolder constructValuesAndSqlFromFilter(
            final Filter filter) {

        HashMap model = new HashMap();
        String sql = beginSql;
        boolean hasWhere = false;
        if (filter.getTitle_substring() != null) {
            model.put("title_param", filter.getTitle_substring());
            if (!hasWhere) sql += " where ";
            sql += titleSearchSql + " and ";
            hasWhere = true;
        }
        if (filter.getId_language() != 0) {
            model.put("id_language_param", filter.getId_language());
            if (!hasWhere) sql += " where ";
            sql += byLanguageSql + " and ";
            hasWhere = true;
        }
        if (!filter.getTopics_ids().isEmpty()) {

            int length = filter.getTopics_ids().size();
            model.put("params_ids", filter.getTopics_ids());
            model.put("len_topics_array_param", length);
            if (!hasWhere) sql += " where ";
            sql += "meetups.id in ( " + topicsListSql + ") and ";
            hasWhere = true;
        }
        if (filter.getTime_from() != null && filter.getTime_to() != null) {
            model.put("start_date_param", filter.getTime_from());
            model.put("end_date_param", filter.getTime_to());
            if (!hasWhere) sql += " where ";
            sql += datesRangeSql + " and ";
            hasWhere = true;
        }
        if (filter.getTime_from() != null && filter.getTime_to() == null) {
            model.put("start_date_param", filter.getTime_from());
            if (!hasWhere) sql += " where ";
            sql += dateFromSql + " and ";
            hasWhere = true;
        }
        if (filter.getTime_from() == null && filter.getTime_to() != null) {
            model.put("end_date_param", filter.getTime_to());
            if (!hasWhere) sql += " where ";
            sql += dateToSql + " and ";
            hasWhere = true;
        }
      boolean isNotSprecifiedRateRange =   (filter.getRate_from() == 0.0 && filter.getRate_to() == 0.0);
        boolean isDefaultRateRange = (filter.getRate_from() == 0.0 && filter.getRate_to() == 5.0);
        boolean isFullRateRange =
            //    (filter.getRate_from() != 0.0 && filter.getRate_to() != 0.0)
            //    &&
                (!isDefaultRateRange);
        boolean isOnlyRateToSpecified = (filter.getRate_from() == 0.0 && filter.getRate_to() != 0.0);
        boolean isOnlyRateFromSpecified = (filter.getRate_from() != 0.0 && filter.getRate_to() != 0.0);
          if(isNotSprecifiedRateRange){

            }
           else if (isFullRateRange) {
            model.put("rate_from", filter.getRate_from());
            model.put("rate_to", filter.getRate_to());
            if (!hasWhere) sql += " where ";
            sql += byRateRangeSql + " and ";
            hasWhere = true;
        } else if (isOnlyRateToSpecified) {
            model.put("rate_to", filter.getRate_to());
            if (!hasWhere) sql += " where ";
            sql += byRateRangeFirstNullSql + " and ";
            hasWhere = true;
        }
    /* if (!hasWhere) sql += " where ";
          sql += onlyScheduledOrBookedSql;*/
      if(hasWhere)  sql = sql.substring(0, sql.length() - 5);//remove the last _AND_
        System.out.println(sql);
        return new SqlAndParamsHolder(sql, model);
    }

}
