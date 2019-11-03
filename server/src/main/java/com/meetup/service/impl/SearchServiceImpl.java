package com.meetup.service.impl;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;
import com.meetup.repository.ISearchDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.ArticleDaoImpl;
import com.meetup.repository.impl.TopicDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.ISearchService;
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
    SearchServiceImpl(@Autowired final UserDaoImpl userDao,
                       @Autowired final ISearchDAO searchDao ) {
        this.userDao = userDao;
        this.searchDao = searchDao;
    }
    @Override
    public  List<Meetup> searchWithFilter(final Filter filter) {
             return searchDao.searchWithFilter(filter);
    }

}
