package com.maruf.localization.dao;

import java.util.List;
import java.util.Map;

public interface LocalizationDao {

    List<Map<String, Object>> findAllLocalizationByProjectIdAndLanguageId(String projectId, String language);

    Map<String, Object> findLocalization(String projectId, String languageId);
}
