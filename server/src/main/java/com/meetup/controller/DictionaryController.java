package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.Language;
import com.meetup.service.IDictionaryService;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * . Rest api for languages
 */
@RestController
@Api(value = "meetup-application")
public class DictionaryController {

    /**
     * . Dictionary operations.
     */
    private IDictionaryService dictionaryService;

    /**
     * . constructor
     *
     * @param dictionaryService custom DictionaryService
     */
    public DictionaryController(final IDictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /**
     * Return a list of all languages.
     *
     * @param sorted if true, the languages will be sorted by names in ascending
     * order
     * @return a ResponseEntity with a list of all languages
     */
    @GetMapping(value = "/api/v1/languages")
    public ResponseEntity<List<Language>> getLanguagesWithIds(
        @RequestParam final Optional<Boolean> sorted) {
        return ok(dictionaryService.getAllLanguages(sorted.orElse(false)));
    }
}
