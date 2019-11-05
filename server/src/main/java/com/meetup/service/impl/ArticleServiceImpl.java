package com.meetup.service.impl;

import com.meetup.entities.Article;
import com.meetup.entities.Commentary;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.entities.dto.ArticleCreationDTO;
import com.meetup.entities.dto.ArticleDisplayDTO;
import com.meetup.entities.dto.CommentaryDisplayDTO;
import com.meetup.entities.dto.UserDTO;
import com.meetup.error.ArticleNotFoundException;
import com.meetup.error.SpeakerOperationNotAllowedException;
import com.meetup.error.UserNotFoundException;
import com.meetup.repository.IArticleDAO;
import com.meetup.repository.ITopicDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.ArticleDaoImpl;
import com.meetup.repository.impl.TopicDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.IArticleService;
import com.meetup.utils.ArticleDTOConverter;
import com.meetup.utils.CommentaryDTOConverter;
import com.meetup.utils.RoleProcessor;
import com.meetup.utils.UserDTOConverter;
import java.util.ArrayList;
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
     * Topic repository.
     */
    private ITopicDAO topicDao;

    /**
     * Constructor.
     *
     * @param userDao User repository.
     * @param articleDao Article repository.
     * @param topicDao Topic repository.
     */
    ArticleServiceImpl(@Autowired final UserDaoImpl userDao,
        @Autowired final ArticleDaoImpl articleDao,
        @Autowired final TopicDaoImpl topicDao) {
        this.userDao = userDao;
        this.articleDao = articleDao;
        this.topicDao = topicDao;
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
        if (user == null) {
            throw new UserNotFoundException();
        }
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
        if (user == null) {
            throw new UserNotFoundException();
        }
        Article currentArticle = articleDao.findArticleByID(articleID);
        if (currentArticle == null) {
            throw new ArticleNotFoundException();
        }
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

    /**
     * Get article, that could be displayed.
     *
     * @param articleID Article ID.
     * @return ArticleDisplayDTO.
     */
    @Override
    public ArticleDisplayDTO getDisplayableArticle(final int articleID) {
        Article article = articleDao.findArticleByID(articleID);
        List<Topic> topics = articleDao.getArticleTopics(articleID);

        User user = userDao.findUserById(article.getAuthorID());
        UserDTO userDTO = UserDTOConverter.convertToUserDTO(user);

        return ArticleDTOConverter
            .convertToArticleDisplayDTO(article, topics, userDTO);
    }

    /**
     * Get all articles.
     *
     * @return List of displayable articles.
     */
    @Override
    public List<ArticleDisplayDTO> getAllDisplayableArticles() {
        List<Article> articles = articleDao.getAllArticles();
        List<ArticleDisplayDTO> displayableArticles = new ArrayList<>();

        for (Article article : articles) {
            User user = userDao.findUserById(article.getAuthorID());
            UserDTO userDTO = UserDTOConverter.convertToUserDTO(user);
            List<Topic> topics = articleDao.getArticleTopics(article.getId());
            displayableArticles
                .add(ArticleDTOConverter.convertToArticleDisplayDTO(
                    article,
                    topics,
                    userDTO));
        }
        return displayableArticles;
    }

    /**
     * Remove article by admin.
     *
     * @param articleID Article ID.
     */
    @Override
    public void removeArticleByAdmin(final int articleID) {
        articleDao.removeArticle(articleID);
    }

    /**
     * Get commentaries of given article.
     *
     * @param articleID Article ID.
     * @return List of displayable commentaries.
     */
    @Override
    public List<CommentaryDisplayDTO> getCommentaries(final int articleID) {
        List<Commentary> commentaries = articleDao
            .getArticleCommentaries(articleID);
        List<CommentaryDisplayDTO> displayableCommentaries = new ArrayList<>();

        for (Commentary commentary : commentaries) {
            User user = userDao.findUserById(commentary.getAuthorID());
            UserDTO userDTO = UserDTOConverter.convertToUserDTO(user);
            displayableCommentaries.add
                (CommentaryDTOConverter.convertToCommentaryDisplayDTO(
                    commentary,
                    userDTO
                ));
        }
        return displayableCommentaries;
    }

    /**
     * Post commentary on article.
     *
     * @param articleID Article ID.
     * @param userLogin User login.
     * @param commentary Commentary.
     */
    @Override
    public void postCommentary(final int articleID, final String userLogin,
        final Commentary commentary) {
        User user = userDao.findUserByLogin(userLogin);
        int userID = user.getId();
        articleDao.addCommentary(articleID, userID, commentary);
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
