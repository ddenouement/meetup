package com.meetup.repository;

import com.meetup.entities.Language;
import java.util.List;

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
}
