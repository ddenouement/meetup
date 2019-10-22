package com.meetup.service.impl;

import static com.meetup.service.RoleProcessor.isSpeaker;

import com.meetup.entities.User;
import com.meetup.entities.dto.ArticleCreationDTO;
import com.meetup.error.SpeakerOperationNotAllowedException;
import com.meetup.repository.IArticleDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.ArticleDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Article service (implementation).
 * Used to manage article functionality.
 */
@Component
public class ArticleServiceImlp implements ArticleService {

    /**
     * User repository.
     */
    private IUserDAO userDao;
    /**
     * User repository.
     */
    private IArticleDAO articleDao;

    /**
     * Constructor.
     * @param userDao
     * User repository.
     * @param articleDao
     * Article repository.
     */
    ArticleServiceImlp(@Autowired final UserDaoImpl userDao,
        @Autowired final ArticleDaoImpl articleDao) {
        this.userDao = userDao;
        this.articleDao = articleDao;
    }
    /**
     * Create, and post article.
     * @param articleCreationDTO
     * Article, that should be posted
     */
    @Override
    public void postArticle(final ArticleCreationDTO articleCreationDTO,
        final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        if (isSpeaker(user)) {
            articleDao.insertNewArticle(articleCreationDTO, user.getId());
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

}
