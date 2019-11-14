package com.meetup.repository;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;
import com.meetup.entities.dto.MeetupDisplayDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * class for working with filters and meetups in DB.
 */
public interface ISearchDAO {

    /**
     * @param filter custom Filter from frontend.
     * @return List of matched meetups
     */
    List<MeetupDisplayDTO> getMeetups(Filter filter, Integer offset, Integer limit) ;

    /**
     * .
     *
     * @param filter Filter to save.
     * @param userId user whose filter it is
     * @return Filter
     */
    Filter saveFilter(Filter filter, int userId);

    /**
     * .
     *
     * @param userId user whose filters we access
     * @return List of Filters
     */
    List<Filter> getFilters(int userId);

    int getAllMeetupsCount(Filter filter);
}
