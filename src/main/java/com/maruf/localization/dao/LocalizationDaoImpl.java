package com.maruf.localization.dao;

import com.maruf.localization.dto.LocalizationDto;
import com.maruf.localization.entity.Localization;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LocalizationDaoImpl implements LocalizationDao  {

    private NamedParameterJdbcTemplate jdbcTemplate;
    public LocalizationDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> findAllLocalizationBylanguage(Long project, Long language) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("projectId", project);
        parameters.put("languageId", language);

        String sql = "SELECT l.id, l.langKey, lv.value FROM LOCALIZATION l " +
                "left join LOCALIZATION_VALUE lv ON (lv.localization_id = l.id and lv.language_id = :languageId) " +
                "WHERE l.project_id = :projectId ";


        return jdbcTemplate.queryForList(sql, parameters);

    }
}
