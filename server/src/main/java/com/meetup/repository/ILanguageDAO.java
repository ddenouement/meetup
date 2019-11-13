package com.meetup.repository;

import com.meetup.entities.Language;
import java.util.List;

/**.
 * Interface for working with DB considering languages
 */
public interface ILanguageDAO {

    /**
     * Retrieve all instances of a type.
     *
     * @return all instances of a type
     */
    List<Language> findAll();

    /**
     * Retrieve all instances of a type sorted by names in ascending order.
     *
     * @return all instances of a type
     */
    List<Language> findAllSorted();

    /**
     * Find language by ID.
     * @param language_id
     * Language ID.
     * @return
     * Language object.
     */
    Language findLanguageByID(Integer language_id);
    /**
     * Find language by ID.
     * @param name
     * Language ID.
     * @return
     * Language object.
     */
    Language findLanguageByName(String name);

    /**
     * Update a language to a new one.
     * @param language new field values for language
     * @param id id of language to update
     * @return updated language
     */
    Language update(Language language, Integer id);

    /**
     * Insert a language in the database.
     * @param language language to insert
     * @return inserted language
     */
    Language insert(Language language);

    /**
     * Delete a language with specified ID from the database.
     * @param id ID of language to delete
     */
    void delete(Integer id);

    /**
     * Find speaker's languages.
     * @param speakerId the id of speaker to find languages
     * @return list of speaker's languages
     */
    List<Language> getSpeakerLanguages(Integer speakerId);
 }
