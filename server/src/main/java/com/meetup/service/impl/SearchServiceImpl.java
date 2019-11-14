package com.meetup.service.impl;

import com.meetup.entities.Filter;
import com.meetup.entities.dto.MeetupDisplayDTO;
import com.meetup.repository.ISearchDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.ISearchService;
import com.meetup.utils.MeetupDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
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
    public  List<MeetupDisplayDTO> getMeetups(final Filter filter,Integer offset, Integer limit) {
             List<MeetupDisplayDTO> meetups =  searchDao.getMeetups(filter, offset, limit);
         return meetups;
    }

    @Override
    public Filter createFilter(Filter filter, int userID) {
        return searchDao.saveFilter(filter, userID);
    }

    @Override
    public List<Filter> getFilters(int userId) {
       return searchDao.getFilters(userId);
    }


}
