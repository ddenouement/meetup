package com.meetup.service;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;

import java.util.List;

public interface ISearchService {
      List<Meetup> searchWithFilter(  Filter filter) ;
}
