package com.meetup.controller;

import com.meetup.error.*;
import com.meetup.utils.constants.ExceptionMessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception controller class. Returns ResponseEntity object with status code.
 */

//TODO logger
@Slf4j
@ControllerAdvice
public class GlobalExceptionController {

    /**
     * MeetupNotFoundException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler(MeetupNotFoundException.class)
    public ResponseEntity<Object> meetupNotFoundException() {
        log.error("An exception occurred!", new MeetupNotFoundException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.MEETUP_NOT_FOUND,
            HttpStatus.NOT_FOUND);
    }

    /**
     * TopicNotFoundException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<Object> topicNotFoundException() {
        log.error("An exception occurred!", new TopicNotFoundException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.TOPIC_NOT_FOUND,
            HttpStatus.NOT_FOUND);
    }

    /**
     * ArticleNotFoundException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Object> articleNotFoundException() {
        log.error("An exception occurred!", new ArticleNotFoundException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.ARTICLE_NOT_FOUND,
            HttpStatus.NOT_FOUND);
    }

    /**
     * OutOfSlotsException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler(OutOfSlotsException.class)
    public ResponseEntity<Object> outOfSlotsException() {
        log.error("An exception occurred!", new OutOfSlotsException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.OUT_OF_SLOTS,
            HttpStatus.FORBIDDEN);
    }

    /**
     * EmailIsUsedException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler(EmailIsUsedException.class)
    public ResponseEntity<Object> emailIsUsedException() {
        log.error("An exception occurred!", new EmailIsUsedException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.EMAIL_IS_USED,
            HttpStatus.FORBIDDEN);
    }

    /**
     * LoginIsUsedException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler(LoginIsUsedException.class)
    public ResponseEntity<Object> loginIsUsedException() {
        log.error("An exception occurred!", new LoginIsUsedException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.LOGIN_IS_USED,
            HttpStatus.FORBIDDEN);
    }

    /**
     * TopicIsUsedException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler(TopicIsUsedException.class)
    public ResponseEntity<Object> topicIsUsedException() {
        log.error("An exception occurred!", new TopicIsUsedException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.TOPIC_IS_USED,
            HttpStatus.FORBIDDEN);
    }

    /**
     * LanguageIsUsedException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler(LanguageIsUsedException.class)
    public ResponseEntity<Object> languageIsUsedException() {
        log.error("An exception occurred!", new LanguageIsUsedException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.LANGUAGE_IS_USED,
            HttpStatus.FORBIDDEN);
    }

    /**
     * AuthenticationException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> authenticationException() {
        log.error("An exception occurred!", new Exception("Unauthorized!"));
        return new ResponseEntity<>(
            ExceptionMessageConstants.INCORRECT_LOGIN_CREDENTIALS,
            HttpStatus.UNAUTHORIZED);
    }

    /**
     * BadgeScriptIsIncorrectException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler({BadgeScriptIsIncorrectException.class})
    public ResponseEntity<Object> badgeScriptIsIncorrectException() {
        log.error("An exception occurred!", new BadgeScriptIsIncorrectException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.INCORRECT_SCRIPT,
            HttpStatus.BAD_REQUEST);
    }

    /**
     * BadgeNameExistsException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler({BadgeNameExistsException.class})
    public ResponseEntity<Object> badgeNameExistsException() {
        log.error("An exception occurred!", new BadgeNameExistsException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.BADGE_NAME_IS_USED,
            HttpStatus.BAD_REQUEST);
    }

    /**
     * AuthenticationException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> userNotFoundException() {
        log.error("An exception occurred!", new UserNotFoundException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.USER_NOT_FOUND,
            HttpStatus.FORBIDDEN);
    }

    /**
     * IllegalMeetupStateException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler({IllegalMeetupStateException.class})
    public ResponseEntity<Object> illegalMeetupStateException() {
        log.error("An exception occurred!", new IllegalMeetupStateException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.ILLEGAL_MEETUP_STATE,
            HttpStatus.BAD_REQUEST);
    }

    /**
     * EmailDoesntExistException controller.
     *
     * @return Response entity with status code.
     */
    @ExceptionHandler({EmailDoesntExistException.class})
    public ResponseEntity<Object> emailDoesntExistException() {
        log.error("An exception occurred!", new EmailDoesntExistException());
        return new ResponseEntity<>(
            ExceptionMessageConstants.EMAIL_NOT_FOUND,
            HttpStatus.BAD_REQUEST);
    }

}
