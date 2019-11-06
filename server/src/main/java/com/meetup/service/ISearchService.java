package com.meetup.service;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;
import com.meetup.entities.dto.MeetupDisplayDTO;

import java.util.List;

public interface ISearchService {
      List<MeetupDisplayDTO> searchWithFilter(Filter filter) ;
      Filter insertFilter(Filter filter, int userID);
      List<Filter> getUserFilters(int userId);
}
