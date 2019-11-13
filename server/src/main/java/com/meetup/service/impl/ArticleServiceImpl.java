package com.meetup.service.impl;

import com.meetup.entities.Commentary;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.entities.dto.ArticleCreationDTO;
import com.meetup.entities.dto.ArticleDisplayDTO;
import com.meetup.entities.dto.CommentaryDisplayDTO;
import com.meetup.error.ArticleNotFoundException;
import com.meetup.repository.IArticleDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.ArticleDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.IArticleService;
import com.meetup.utils.CommentaryDTOConverter;
import java.util.List;
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
        articleDao.insertNewArticle(articleCreationDTO, user.getId());
    }

    /**
     * Remove article by speaker.
     *
     * @param articleID Article ID, that should be removed
     */
    @Override
    public void removeArticle(final int articleID) {
        if (articleDao.findArticleByID(articleID) == null) {
            throw new ArticleNotFoundException();
        } else {
            articleDao.removeArticle(articleID);
        }
    }

    /**
     * Get article, that could be displayed.
     *
     * @param articleID Article ID.
     * @return ArticleDisplayDTO.
     */
    @Override
    public ArticleDisplayDTO getDisplayableArticle(final int articleID) {
        ArticleDisplayDTO article = articleDao.findArticleByID(articleID);
        if (article == null){
            throw new ArticleNotFoundException();
        }
        List<Topic> topics = articleDao.getArticleTopics(articleID);
        article.setTopics(topics);
        return article;
    }

    /**
     * Get all articles.
     *
     * @return List of displayable articles.
     */
    @Override
    public List<ArticleDisplayDTO> getAllDisplayableArticles() {
        List<ArticleDisplayDTO> articles = articleDao.getAllArticles();
        for (ArticleDisplayDTO article : articles) {
            List<Topic> topics = articleDao.getArticleTopics(article.getId());
            article.setTopics(topics);
        }
        return articles;
    }

    @Override
    public List<ArticleDisplayDTO> getAllDisplayableArticlesByPages(int limit,
        int offset) {
        List<ArticleDisplayDTO> articles = articleDao
            .getAllArticlesByPages(limit, offset);
        for (ArticleDisplayDTO article : articles) {
            List<Topic> topics = articleDao.getArticleTopics(article.getId());
            article.setTopics(topics);
        }
        return articles;
    }

    @Override
    public int getAllArticlesCount() {
        return articleDao.getAllArticlesCount();
    }

    /**
     * Remove article by admin.
     *
     * @param articleID Article ID.
     */
    @Override
    public void removeArticleByAdmin(final int articleID) {
        if (articleDao.findArticleByID(articleID) == null) {
            throw new ArticleNotFoundException();
        } else {
            articleDao.removeArticle(articleID);
        }
    }

    /**
     * Get commentaries of given article.
     *
     * @param articleID Article ID.
     * @return List of displayable commentaries.
     */
    @Override
    public List<CommentaryDisplayDTO> getCommentaries(final int articleID) {
        return articleDao.getArticleCommentaries(articleID);
    }

    /**
     * Post commentary on article.
     *
     * @param articleID Article ID.
     * @param userLogin User login.
     * @param commentary Commentary.
     */
    @Override
    public CommentaryDisplayDTO postCommentary(final int articleID,
        final String userLogin,
        final Commentary commentary) {
        User user = userDao.findUserByLogin(userLogin);
        int userID = user.getId();
        Commentary commentCreated;
        if (articleDao.findArticleByID(articleID) == null) {
            throw new ArticleNotFoundException();
        } else {
            commentCreated = articleDao
                .addCommentary(articleID, userID, commentary);
        }
        return CommentaryDTOConverter
            .convertToCommentaryDisplayDTO(commentCreated, user);
    }

    /**
     * Remove commentary on article.
     *
     * @param commentID Commentary ID.
     */
    @Override
    public void removeCommentary(final int commentID) {
        articleDao.removeCommentary(commentID);
    }
}
