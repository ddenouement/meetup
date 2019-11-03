package com.meetup.repository;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;

import java.util.List;

/**
 * class for working with filters and meetups in DB.
 */
public interface ISearchDAO {
    /**
     *
     * @param filter custom Filter from frontend.
     * @return List of matched meetups
     */
      List<Meetup> searchWithFilter(  Filter filter) ;
}
