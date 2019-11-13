package com.meetup.service;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;
import com.meetup.entities.dto.MeetupDisplayDTO;

import java.sql.SQLException;
import java.util.List;

public interface ISearchService {
      List<MeetupDisplayDTO > searchWithFilter(Filter filter) throws SQLException;
      Filter insertFilter(Filter filter, int userID);
      List<Filter> getUserFilters(int userId);
}
