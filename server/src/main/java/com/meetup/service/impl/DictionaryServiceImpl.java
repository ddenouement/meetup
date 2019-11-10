package com.meetup.service.impl;

import com.meetup.entities.Language;
import com.meetup.error.LanguageIsUsedException;
import com.meetup.repository.ILanguageDAO;
import com.meetup.service.IDictionaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**.
 * implementation of DictionaryServiceInterface
 */
@Service
public class DictionaryServiceImpl implements IDictionaryService {

    /**.
     * Language repository.
     */
    private ILanguageDAO languageDao;

    /**.
     * constructor
     * @param languageDao ILanguageDao
     */
    @Autowired
    public DictionaryServiceImpl(final ILanguageDAO languageDao) {
        this.languageDao = languageDao;
    }

    /**.
     * Return a list of all languages.
     * @param sorted if true, the languages will be sorted by name in ascending
     * order.
     * @return a list of all languages
     */
    @Override
    public List<Language> getAllLanguages(final boolean sorted) {
        if (sorted) {
            return languageDao.findAllSorted();
        } else {
            return  languageDao.findAll();
        }
    }

    @Override
    public Language getLanguageByID(Integer id) {
        return languageDao.findLanguageByID(id);
    }

    @Override
    public Language update(Language language, Integer id) {
        return languageDao.update(language, id);
    }

    @Override
    public Language insert(Language language) {
        Language created;
        if (languageDao.findLanguageByName(language.getName()) != null) {
            throw new LanguageIsUsedException();
        } else {
            created = languageDao.insert(language);
        }
        return created;

    }

    @Override
    public void delete(Integer id) {
        if (languageDao.findLanguageByID(id) != null) {
            throw new LanguageIsUsedException();
        }
        languageDao.delete(id);
    }

    @Override
    public List<Language> getSpeakerLanguages(Integer speakerId) {
        return languageDao.getSpeakerLanguages(speakerId);
    }
}
