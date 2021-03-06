package com.meetup.controller;

import com.meetup.entities.Commentary;
import com.meetup.entities.dto.ArticleCreationDTO;
import com.meetup.entities.dto.ArticleDisplayDTO;
import com.meetup.entities.dto.CommentaryDisplayDTO;
import com.meetup.service.impl.ArticleServiceImpl;
import com.meetup.service.impl.LoginValidatorServiceImpl;
import com.meetup.utils.constants.ModelConstants;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for Articles.
 */
@RestController
@Api(value = "meetup-application")
@RequestMapping("/api/v1")
public class ArticleController {

    /**
     * Article service.
     */
    private ArticleServiceImpl articleService;

    /**
     * Login validator service service.
     */
    private LoginValidatorServiceImpl loginValidatorService;

    /**
     * Constructor.
     *
     * @param loginValidatorService LoginValidatorService.
     * @param articleService ArticleService.
     */
    ArticleController(
        @Autowired final LoginValidatorServiceImpl loginValidatorService,
        @Autowired final ArticleServiceImpl articleService) {
        this.loginValidatorService = loginValidatorService;
        this.articleService = articleService;
    }

    /**
     * Create and post new Article from speaker.
     *
     * @param token JSON web token.
     * @param articleCreationDTO ArticleDTO that should be created
     * @return ResponseEntity with status code.
     */
    @PreAuthorize("hasAuthority(T(com.meetup.utils.Role).SPEAKER)")
    @PostMapping(value = "/user/speaker/articles")
    public ResponseEntity postArticleBySpeaker(
        @CookieValue("token") final String token,
        @RequestBody final ArticleCreationDTO articleCreationDTO) {
        String userLogin = loginValidatorService.extractLogin(token);
        articleService.postArticle(articleCreationDTO, userLogin);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Remove article by speaker.
     *
     * @param articleID Article ID that should be removed.
     * @return ResponseEntity with status code.
     */
    @PreAuthorize("hasAuthority(T(com.meetup.utils.Role).SPEAKER)")
    @DeleteMapping(value = "/user/speaker/articles/{id}")
    public ResponseEntity removeArticleBySpeaker(
        @PathVariable("id") final int articleID) {
        articleService.removeArticle(articleID);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Admin can remove article by Id.
     *
     * @param articleID Article ID.
     * @return ResponseEntity with status code.
     */
    @PreAuthorize("hasAuthority(T(com.meetup.utils.Role).ADMIN)")
    @DeleteMapping(value = "/user/articles/{id}")
    public ResponseEntity removeArticleByAdmin(
        @PathVariable("id") final int articleID) {
        articleService.removeArticleByAdmin(articleID);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Get all displayable articles.
     *
     * @return Article list.
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/articles")
    public ResponseEntity<List<ArticleDisplayDTO>> getAllArticles() {
        return new ResponseEntity<>(articleService.getAllDisplayableArticles(),
            HttpStatus.OK);
    }

    /**
     * Get articles, depending on page size.
     *
     * @return ResponseEntity<HashMap   <   List   <   ArticleDisplayDTO>, Integer>>
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/articles", params = {"pagesize", "page"})
    public ResponseEntity getArticlesByPages(
        @RequestParam("pagesize") final int pageSize,
        @RequestParam("page") final int currentPage) {
        int offset = pageSize * (currentPage - 1);
        int count = articleService.getAllArticlesCount();
        List<ArticleDisplayDTO> articles = articleService
            .getAllDisplayableArticlesByPages(pageSize, offset);
        Map<Object, Object> model = new HashMap<>();
        model.put(ModelConstants.articlesCount, count);
        model.put(ModelConstants.articles, articles);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /**
     * Get displayable article
     *
     * @param articleID Article ID.
     * @return Article.
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/user/articles/{id}")
    public ResponseEntity<ArticleDisplayDTO> getArticle(
        @PathVariable("id") final int articleID) {
        return new ResponseEntity<>(
            articleService.getDisplayableArticle(articleID),
            HttpStatus.OK);
    }

    /**
     * Get comments of specific articles.
     *
     * @param articleID Article ID.
     * @return List of commentaries of article.
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "user/articles/{id}/comments")
    public ResponseEntity<List<CommentaryDisplayDTO>> getCommentariesOfArticle(
        @PathVariable("id") final int articleID) {
        return new ResponseEntity<>(articleService.getCommentaries(articleID),
            HttpStatus.OK);
    }

    /**
     * Post commentary on article.
     *
     * @param commentary Commentary.
     * @param token JSON web token.
     * @param articleID Article ID.
     * @return Response entity with status code.
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "user/articles/{id}/comments")
    public ResponseEntity<CommentaryDisplayDTO> postCommentaryOnArticle(
        @RequestBody final Commentary commentary,
        @CookieValue("token") final String token,
        @PathVariable("id") final int articleID) {
        String userLogin = loginValidatorService.extractLogin(token);
        return new ResponseEntity<>(
            articleService.postCommentary(articleID, userLogin, commentary),
            HttpStatus.CREATED);
    }

    /**
     * Delete commentary on article.
     *
     * @param commentID Commentary ID.
     * @return Response entity with status code.
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @DeleteMapping(value = "user/articles/comments/{id}")
    public ResponseEntity removeCommentaryFromArticle(
        @PathVariable("id") final int commentID) {
        articleService.removeCommentary(commentID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
