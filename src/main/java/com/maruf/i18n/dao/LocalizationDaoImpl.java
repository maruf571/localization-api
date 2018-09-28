package com.maruf.i18n.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LocalizationDaoImpl implements LocalizationDao  {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public LocalizationDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> findAllLocalizationByProjectIdAndLanguageId(Long projectId, Long languageId) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("projectId", projectId);
        parameters.put("languageId", languageId);

        String sql = "SELECT l.id, l.langKey, lv.value FROM LOCALIZATION_KEY l " +
                " LEFT JOIN  LOCALIZATION_VALUE lv ON (lv.localizationKey_id = l.id and lv.language_id = :languageId) " +
                " WHERE l.project_id = :projectId ";
        return jdbcTemplate.queryForList(sql, parameters);
    }

}
