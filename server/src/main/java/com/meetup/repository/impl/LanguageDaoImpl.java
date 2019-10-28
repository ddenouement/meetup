package com.meetup.repository.impl;

import com.meetup.entities.Language;
import com.meetup.model.mapper.LanguageMapper;
import com.meetup.repository.ILanguageDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
     * @param languageID
     * Language ID.
     * @return
     * Language object.
     */
    @Override
    public Language findLanguageByID(final int languageID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id", languageID);
        return this.template
            .queryForObject(findLanguageByID, param, new LanguageMapper());
    }

}
