//package com.meetup.service;
//
//import com.meetup.entities.Language;
//import com.meetup.entities.User;
//import java.util.List;
//
///**
// * Interface to work with language in the database.
// */
//public interface ILanguageService {
//
//    /**
//     * Return a language with specified ID.
//     * @param id ID of language to return
//     * @return a language with specified ID
//     */
//    Language findLanguageByID(Integer id);
//
//    /**
//     * Return all languages.
//     * @return a list of all languages
//     */
//    List<Language> findAll();
//
//    /**
//     * Return all languages sorted by names in ascending order.
//     * @return a list of sorted languages
//     */
//    List<Language> findAllSorted();
//
//    /**
//     * Update a language to a new one.
//     * @param language new field values for language
//     * @param id id of language to update
//     * @return updated language
//     */
//    Language update(Language language, Integer id);
//
//    /**
//     * Insert a language.
//     * @param language language to insert
//     * @return inserted language
//     */
//    Language insert(Language language);
//
//    /**
//     * Delete a language with specified ID.
//     * @param id ID of language to delete
//     */
//    void delete(Integer id);
//
//    /**
//     * Find all speaker's languages.
//     * @param speakerId the id of speaker to compute languages for
//     * @return list of speaker's languages
//     */
//    List<Language> getSpeakerLanguages(Integer speakerId);
//}
