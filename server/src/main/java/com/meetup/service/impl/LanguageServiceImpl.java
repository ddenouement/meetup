//package com.meetup.service.impl;
//
//import com.meetup.entities.Language;
//import com.meetup.entities.User;
////import com.meetup.error.BadgeScriptIsIncorrectException;
//import com.meetup.repository.ILanguageDAO;
//import com.meetup.service.ILanguageService;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class LanguageServiceImpl implements ILanguageService {
//
//    /** language repository. */
//    private ILanguageDAO languageDAO;
//
//    /**
//     * Initialize service.
//     * @param languageDAO language repository.
//     */
//    @Autowired
//    public LanguageServiceImpl(final ILanguageDAO languageDAO) {
//        this.languageDAO = languageDAO;
//    }
//
//    /**
//     * Return a language with specified ID.
//     * @param id ID of language to return
//     * @return a language with specified ID
//     */
//    public Language findLanguageByID(final Integer id) {
//        return languageDAO.findLanguageByID(id);
//    }
//
//    /**
//     * Return all languages.
//     * @return a list of all languages
//     */
//    @Override
//    public List<Language> findAll() {
//        return languageDAO.findAll();
//    }
//
//    /**
//     * Return all languages sorted by names in ascending order.
//     * @return a list of sorted languages
//     */
//    @Override
//    public List<Language> findAllSorted() {
//        return languageDAO.findAllSorted();
//    }
//
//    /**
//     * Update a language to a new one.
//     * @param language new field values for language
//     * @param id id of language to update
//     * @return updated language
//     */
//    @Override
//    public Language update(final Language language, final Integer id) {
//        return languageDAO.update(language, id);
//    }
//
//    /**
//     * Insert a language.
//     * @param language language to insert
//     * @return inserted language
//     */
//    @Override
//    public Language insert(final Language language) {
//                return languageDAO.insert(language);
//    }
//
//    /**
//     * Delete a language with specified ID.
//     * @param id ID of language to delete
//     */
//    @Override
//    public void delete(final Integer id) {
//
//        languageDAO.delete(id);
//    }
//
//    /**
//     * Find all speaker's languages.
//     * @param speakerId the id of speaker to compute languages for
//     * @return list of speaker's languages
//     */
//    @Override
//    public List<Language> getSpeakerLanguages(final Integer speakerId) {
//        return languageDAO.getSpeakerLanguages(speakerId);
//    }
//
//}
