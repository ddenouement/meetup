package com.meetup.service;

import com.meetup.entities.Language;
import java.util.List;

/**.
 * interface to work with languages
 */
public interface IDictionaryService {

    /**.
     * Return all languages.
     *
     * @param sorted if true, the languages will be sorted by name in ascending
     * order.
     * @return a list of all languages
     */
    List<Language> getAllLanguages(boolean sorted);
}
