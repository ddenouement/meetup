package com.meetup.service.impl;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;
import com.meetup.entities.dto.MeetupDisplayDTO;
import com.meetup.repository.ISearchDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.ISearchService;
import com.meetup.utils.MeetupDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchServiceImpl implements ISearchService {
    /**
     * User repository.
     */
    private IUserDAO userDao;
    /**
     * Search with filters repository.
     */
    private ISearchDAO searchDao;
    /**
     * Meetup DTO converter.
     */
    private MeetupDTOConverter meetupDTOConverter;


    SearchServiceImpl(@Autowired final UserDaoImpl userDao,
                       @Autowired final ISearchDAO searchDao,
                      @Autowired final MeetupDTOConverter meetupDTOConverter
                      ) {
        this.userDao = userDao;
        this.searchDao = searchDao;
        this.meetupDTOConverter = meetupDTOConverter;

    }
    @Override
    public  List<MeetupDisplayDTO> searchWithFilter(final Filter filter) {
             List<Meetup> meetups =  searchDao.searchWithFilter(filter);
        return   meetupDTOConverter.convertToMeetupDTO(meetups) ;
    }

    @Override
    public Filter insertFilter(Filter filter, int userID) {
        return searchDao.saveFilterToCurrentUser(filter, userID);
    }

    @Override
    public List<Filter> getUserFilters(int userId) {
       return searchDao.getUserFiltersSaved(userId);
    }


}
