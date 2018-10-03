package com.maruf.i18n.localization.dao;

import java.util.List;
import java.util.Map;

public interface LocalizationDao {

    List<Map<String, Object>> findAllLocalizationByProjectIdAndLanguageId(String language);

    Map<String, Object> findLocalization(String projectId, String languageId);
}
