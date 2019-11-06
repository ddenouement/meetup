package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.Language;
import com.meetup.service.IDictionaryService;
import com.meetup.service.impl.LoginValidatorServiceImpl;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * . Rest api for languages
 */
@RestController
@Api(value = "meetup-application")
@RequestMapping("/api/v1")
public class DictionaryController {

    /**
     * . Dictionary operations.
     */
    private IDictionaryService dictionaryService;

    /**
     * Login validator service service.
     */
    private LoginValidatorServiceImpl loginValidatorService;

    /**
     * . constructor
     *
     * @param dictionaryService custom DictionaryService
     */
    @Autowired
    public DictionaryController(final IDictionaryService dictionaryService, final LoginValidatorServiceImpl loginValidatorService) {
        this.dictionaryService = dictionaryService;
        this.loginValidatorService = loginValidatorService;

    }

    /**
     * Return a list of all languages.
     *
     * @param sorted if true, the languages will be sorted by names in ascending
     * order
     * @return a ResponseEntity with a list of all languages
     */
    @GetMapping(value = "/languages")
    public ResponseEntity<List<Language>> getLanguagesWithIds(
        @RequestParam final Optional<Boolean> sorted) {
        return ok(dictionaryService.getAllLanguages(sorted.orElse(false)));
    }

    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @GetMapping("/language/{id}")
    public ResponseEntity<Language> findLanguageByID(
            @PathVariable("id") final Integer id) {
        Language language = dictionaryService.getLanguageByID(id);
        if (language == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ok(language);
    }

    /**
     * Update a language to a new one.
     * @param language new field values for language
     * @param id id of language to update
     * @return updated language
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @PutMapping("/language/{id}")
    public ResponseEntity<Language> updateLanguage(
            @PathVariable("id") final Integer id,
            @RequestBody final Language language) {
        return new ResponseEntity<>(dictionaryService.update(language, id),
                HttpStatus.OK);
    }

    /**
     * Insert a language.
     * @param language language to insert
     * @return inserted language
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @PostMapping("/language")
    public ResponseEntity<Language> insertLanguage(
            @RequestBody final Language language) {
        return new ResponseEntity<>(dictionaryService.insert(language),
                HttpStatus.CREATED);
    }

    /**
     * Delete a language with specified ID.
     * @param id ID of language to delete
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @DeleteMapping("/language/{id}")
    public ResponseEntity deleteLanguage(@PathVariable("id") final Integer id) {
        dictionaryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Find all speaker's languages.
     * @return list of speaker's languages
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
            + "T(com.meetup.utils.Role).SPEAKER, "
            + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping("/user/languages")
    public ResponseEntity<List<Language>> getSpeakerLanguages(@CookieValue("token") final String token) {
        int idSpeaker = loginValidatorService.extractId(token);

        return ok(dictionaryService.getSpeakerLanguages(idSpeaker));
    }

}
