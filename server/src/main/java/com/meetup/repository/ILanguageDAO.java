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
     * @param languageID
     * Language ID.
     * @return
     * Language object.
     */
    Language findLanguageByID(int languageID);
}
