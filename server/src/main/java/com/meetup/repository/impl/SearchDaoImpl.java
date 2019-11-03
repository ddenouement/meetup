package com.meetup.repository.impl;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;
import com.meetup.model.mapper.MeetupMapper;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.ISearchDAO;
import com.meetup.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

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

    @Autowired
    IMeetupDAO meetupDAO;


    /**
     * @param filter custom Filter from frontend.
     * @return List of matched meetups
     */
    @Override
    public List<Meetup> searchWithFilter(final Filter filter) {
        Pair<String, Map<String, ?>> vals = constructValuesAndSqlFromFilter(filter);
        SqlParameterSource param = new MapSqlParameterSource()
                .addValues(vals.getSecond());

        List<Meetup> foundmeetups =
                template.query(vals.getFirst(), param, new MeetupMapper());
        for (Meetup m : foundmeetups){
            m.setTopics(meetupDAO.getMeetupTopics(m.getId()));
        }
        return foundmeetups;
    }

    public Pair<String, Map<String, ?>> constructValuesAndSqlFromFilter(final Filter filter) {

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
            model.put("id_language_param", filter.getTitle_substring());
            if (!hasWhere) sql += " where ";
            sql += byLanguageSql + " and ";
            hasWhere = true;
        }
        if (!filter.getTopics_ids().isEmpty()) {

            int length = filter.getTopics_ids().size();
            model.put("params_ids", filter.getTopics_ids());
            model.put("len_topics_array_param", length);
      //      topicsListSql=any(topicsListSql, filter.getTopics_ids().size());
     //       model.put("len_topics_array_param", filter.getTopics_ids().size());
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
        //process speakers rate range if it is not default(0-5)
        if (filter.getRate_from() != 0.0 && filter.getRate_to() != 5.0) {
            model.put("rate_from", filter.getRate_from());
            model.put("rate_to", filter.getRate_to());
            if (!hasWhere) sql += " where ";
            sql += byRateRangeSql + " and ";
            hasWhere = true;
        }
        //so that new speakers with null rate get a chance
        if (filter.getRate_from() < 0.2) {
            model.put("rate_to", filter.getRate_to());
            if (!hasWhere) sql += " where ";
            sql += byRateRangeFirstNullSql + " and ";
            hasWhere = true;
        }
        if (!hasWhere) sql += " where ";
   //     sql += onlyScheduledOrBookedSql;
           sql = sql.substring(0, sql.length() - 5);//remove the last _AND_ (needed only if no onlyScheduledOrBookedSql)

        return new Pair<>(sql, model);
    }

}
