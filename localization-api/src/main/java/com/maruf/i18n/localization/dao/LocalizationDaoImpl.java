package com.maruf.i18n.localization.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class LocalizationDaoImpl implements LocalizationDao  {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public LocalizationDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Use for protected api
     * @param languageId
     * @return
     */
    @Override
    public List<Map<String, Object>> findAllLocalizationByProjectIdAndLanguageId(String projectId, String languageId) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("languageId", languageId);
        parameters.put("projectId", projectId);

        String sql =
                " SELECT lk.id, lk.langKey, lv.value " +
                " FROM LOCALIZATION_KEY lk " +
                " LEFT JOIN LOCALIZATION_VALUE lv ON (lv.localizationKey_id = lk.id and lv.language_id = :languageId) " +
                " WHERE " +
                " lk.project_id=:projectId";
        return jdbcTemplate.queryForList(sql, parameters);
    }


    /**
     * use for public api
     *
     * @param projectId
     * @param languageId
     * @return
     */
    public Map<String, Object> findLocalization(String projectId, String languageId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("projectId", projectId);
        parameters.put("languageId", languageId);
        log.debug("parameters: {}", parameters);

        Map<String, Object> map = new HashMap<>();
        String sql =
                "SELECT lk.langKey, lv.value " +
                        " FROM LOCALIZATION_KEY lk " +
                        " LEFT JOIN LOCALIZATION_VALUE lv ON (lv.localizationKey_id=lk.id and lv.language_id = :languageId) " +
                        " WHERE " +
                        " lk.project_id=:projectId ";
        jdbcTemplate.query(sql, parameters, rs -> {
            map.put(rs.getString("langKey"), rs.getString("value"));
        });

        return map;
    }


}
