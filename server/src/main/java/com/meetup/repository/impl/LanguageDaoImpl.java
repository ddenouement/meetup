package com.meetup.repository.impl;

import com.meetup.entities.Language;
import com.meetup.error.ElementIsUsedException;
import com.meetup.error.LanguageIsUsedException;
import com.meetup.model.mapper.LanguageMapper;
import com.meetup.repository.ILanguageDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import com.meetup.utils.DbQueryConstants ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
/**.
 * Implemetation of ILanguageDAO
 */
@Repository
@PropertySource("classpath:sql/language_queries.properties")
public class LanguageDaoImpl implements ILanguageDAO {

    /**.
     * Provides JDBC operations with named parameters.
     **/
    private NamedParameterJdbcTemplate template;

    /**.
     * Initialize with the instance of NamedParameterJdbcTemplate.
     *
     * @param template template to use to perform JDBC operations.
     */
    @Autowired
    public LanguageDaoImpl(final NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    /**.
     *  SQL script to select all rows in table languages. */
    @Value("${find_all_languages}")
    private String findAllScript;

    /** .
     * SQL script to select all rows in table languages and sort them by names
     * in ascending order. **/
    @Value("${find_all_languages_sorted}")
    private String findAllSortedScript;

    /**
     * SQL script to select language by ID.
     */
    @Value("${find_language_by_id}")
    private String findLanguageByID;
    /**
     * SQL script to select language by ID.
     */
    @Value("${find_language_by_name}")
    private String findLanguageByName;

    /**
     * SQL script to insert a row in table languages.
     */
    @Value("${insert_language}")
    private String insertLanguage;

    /**
     * SQL script to update a row in table languages.
     */
    @Value("${update_language}")
    private String updateLanguage;


    /**
     * SQL script to delete a rows with specified id in table languages.
     */
    @Value("${delete_language}")
    private String deleteLanguage;

    /**
     * SQL script to select all languages that a specified speaker has.
     */
    @Value("${get_speaker_languages}")
    private String getSpeakerLanguages;

    /**.
     * Retrieve all instances of a type.
     *
     * @return all instances of a type
     */
    @Override
    public List<Language> findAll() {
        return this.template.query(findAllScript, new LanguageMapper());
    }

    /**.
     * Retrieve all instances of a type sorted by names in ascending order.
     *
     * @return all instances of a type
     */
    @Override
    public List<Language> findAllSorted() {
        return this.template.query(findAllSortedScript, new LanguageMapper());
    }

    /**
     * Get language by ID.
     * @param language_id
     * Language ID.
     * @return
     * Language object.
     */
    @Override
    public Language findLanguageByID(final Integer language_id) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), language_id);
        return this.template
            .queryForObject(findLanguageByID, param, new LanguageMapper());
    }

    @Override
    public Language findLanguageByName(final String name) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue(DbQueryConstants.name.name(), name);

        List<Language> languagesSameName =
                template.query(findLanguageByName, param, new LanguageMapper());
        if(languagesSameName==null||languagesSameName.isEmpty())
             return null;
        else return languagesSameName.get(0);
    }

    /**
     * Update a language to a new one.
     *  @param language new field values for language
     * @param language_id id of language to update
     * @return updated language
     */
    @Override
    public Language update(final Language language, final Integer language_id){
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue(DbQueryConstants.id.name(), language_id)
                .addValue(DbQueryConstants.name.name(), language.getName());
        template.update(updateLanguage, param, holder, new String[]
                {DbQueryConstants.id.name()});
        language.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return language;
    }
    /**
     * Insert a language in the database.
     *
     * @param language language to insert
     * @return inserted language
     */
    @Override
    public Language insert(final Language language) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue(DbQueryConstants.name.name(), language.getName());
        template.update(insertLanguage, param, holder, new String[]
                {DbQueryConstants.id.name()});
        language.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return language;
    }

    /**
     * Delete a language with specified ID from the database.
     *
     * @param id_language ID of language to delete
     */
    @Override
    public void delete(final Integer id_language) {
        try {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue(DbQueryConstants.id.name(), id_language);
            template.update(deleteLanguage, param);
        }
catch (DataAccessException e)
        {
            throw new LanguageIsUsedException();
        }
    }

    /**
     * Find all speaker's languages.
     *
     * @param speakerId the id of speaker to compute languages for
     * @return list of speaker's languages
     */
    @Override
    public List<Language> getSpeakerLanguages(final Integer speakerId) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue(DbQueryConstants.id.name(), speakerId);
        return template.query(getSpeakerLanguages, param, new LanguageMapper());
    }


}
