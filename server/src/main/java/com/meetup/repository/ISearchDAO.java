package com.meetup.repository;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;

import java.util.List;

/**
 * class for working with filters and meetups in DB.
 */
public interface ISearchDAO {

    /**
     * @param filter custom Filter from frontend.
     * @return List of matched meetups
     */
    List<Meetup> searchWithFilter(Filter filter);

    /**
     * .
     *
     * @param filter Filter to save.
     * @param userId user whose filter it is
     * @return Filter
     */
    Filter saveFilterToCurrentUser(Filter filter, int userId);

    /**
     * .
     *
     * @param userId user whose filters we access
     * @return List of Filters
     */
    List<Filter> getUserFiltersSaved(int userId);
}
