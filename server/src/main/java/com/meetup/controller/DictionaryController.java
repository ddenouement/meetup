package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.Language;
import com.meetup.service.IDictionaryService;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/api/v1/languages")
    public ResponseEntity postNewLang(
            @RequestBody Language language){
        language.setId(new Random().nextInt());
        HashMap model  = new HashMap();
        model.put("language", language);
        return ok(model);
    }
    @DeleteMapping(value = "/api/v1/languages/{id}")
    public ResponseEntity delLang(
            @PathVariable int id ){
        HashMap model  = new HashMap();
        model.put("id", id);
        //   throw new UserNotFoundException();
        return   ok(model);
    }
    @PutMapping(value = "/api/v1/languages")
    public ResponseEntity editLang(
            @RequestBody Language l){
        HashMap model  = new HashMap();

        model.put("language", l);
        return ok( model);
    }

}
