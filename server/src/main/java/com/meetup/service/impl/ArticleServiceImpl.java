package com.meetup.service.impl;
import com.meetup.entities.Article;
import com.meetup.entities.User;
import com.meetup.entities.dto.ArticleCreationDTO;
import com.meetup.error.SpeakerOperationNotAllowedException;
import com.meetup.repository.IArticleDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.ArticleDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.IArticleService;
import com.meetup.utils.RoleProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Article service (implementation). Used to manage article functionality.
 */
@Component
public class ArticleServiceImpl implements IArticleService {

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
     *
     * @param userDao User repository.
     * @param articleDao Article repository.
     */
    ArticleServiceImpl(@Autowired final UserDaoImpl userDao,
        @Autowired final ArticleDaoImpl articleDao) {
        this.userDao = userDao;
        this.articleDao = articleDao;
    }

    /**
     * Create, and post article.
     *
     * @param articleCreationDTO Article, that should be posted
     */
    @Override
    public void postArticle(final ArticleCreationDTO articleCreationDTO,
        final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        if (RoleProcessor.isSpeaker(user)) {
            articleDao.insertNewArticle(articleCreationDTO, user.getId());
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

    /**
     * Remove article by speaker.
     *
     * @param articleID Article ID, that should be removed
     * @param userLogin User login, that removes an article.
     */
    @Override
    public void removeArticle(final int articleID,
        final String userLogin) {
        User user = userDao.findUserByLogin(userLogin);
        Article currentArticle = articleDao.findArticleByID(articleID);
        if (RoleProcessor.isSpeaker(user)) {
            if (user.getId() == currentArticle.getAuthorID()) {
                articleDao.removeArticle(articleID);
            } else {
                throw new SpeakerOperationNotAllowedException();
            }
        } else {
            throw new SpeakerOperationNotAllowedException();
        }
    }

}
