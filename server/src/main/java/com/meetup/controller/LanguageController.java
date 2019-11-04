//package com.meetup.controller;
//
//import static org.springframework.http.ResponseEntity.ok;
//
//import com.meetup.entities.Language;
//import com.meetup.entities.User;
//import com.meetup.service.ILanguageService;
//import io.swagger.annotations.Api;
//import java.util.List;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Rest api for languages.
// */
//@RestController
//@Api(value = "meetup-application")
//public class LanguageController {
//
//    /**
//     * language operations.
//     */
//    private ILanguageService languageService;
//
//    /**
//     * Initialixe controller.
//     *
//     * @param languageService language operations
//     */
//    public LanguageController(final ILanguageService languageService) {
//        this.languageService = languageService;
//    }
//
//    /**
//     * Return all languages.
//     * @return a list of all languages
//     */
//    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
//    @GetMapping("/api/v1/languages")
//    public ResponseEntity<List<Language>> getLanguages() {
//        return ok(languageService.findAll());
//    }
//
//    /**
//     * Return all languages sorted by names in ascending order.
//     * @return a list of sorted languages
//     */
//    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
//    @GetMapping("/api/v1/languages/sorted")
//    public ResponseEntity<List<Language>> getLanguagesSorted() {
//        return ok(languageService.findAllSorted());
//    }
//
//    /**
//     * Return a language with specified ID.
//     * @param id ID of language to return
//     * @return a language with specified ID
//     */
//    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
//    @GetMapping("/api/v1/language/{id}")
//    public ResponseEntity<Language> findLanguageByID(
//            @PathVariable("id") final Integer id) {
//        Language language = languageService.findLanguageByID(id);
//        if (language == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ok(language);
//    }
//
//    /**
//     * Update a language to a new one.
//     * @param language new field values for language
//     * @param id id of language to update
//     * @return updated language
//     */
//    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
//    @PutMapping("/api/v1/language/{id}")
//    public ResponseEntity<Language> updateLanguage(
//            @PathVariable("id") final Integer id,
//            @RequestBody final Language language) {
//        return new ResponseEntity<>(languageService.update(language, id),
//                HttpStatus.OK);
//    }
//
//    /**
//     * Insert a language.
//     * @param language language to insert
//     * @return inserted language
//     */
//    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
//    @PostMapping("/api/v1/language")
//    public ResponseEntity<Language> insertLanguage(
//            @RequestBody final Language language) {
//        return new ResponseEntity<>(languageService.insert(language),
//                HttpStatus.CREATED);
//    }
//
//    /**
//     * Delete a language with specified ID.
//     * @param id ID of language to delete
//     */
//    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
//    @DeleteMapping("/api/v1/language/{id}")
//    public ResponseEntity deleteLanguage(@PathVariable("id") final Integer id) {
//        languageService.delete(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    /**
//     * Find all speaker's languages.
//     * @param speakerId the id of speaker to compute languages for
//     * @return list of speaker's languages
//     */
//    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
//            + "T(com.meetup.utils.Role).SPEAKER, "
//            + "T(com.meetup.utils.Role).LISTENER)")
//    @GetMapping("/api/v1/user/{id}/languages")
//    public ResponseEntity<List<Language>> getSpeakerLanguages(
//            @PathVariable("id") final Integer id) {
//        return ok(languageService.getSpeakerLanguages(id));
//    }
//
//}
