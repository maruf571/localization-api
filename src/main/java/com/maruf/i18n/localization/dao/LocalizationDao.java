package com.maruf.i18n.localization.dao;

import java.util.List;
import java.util.Map;

public interface LocalizationDao {

    List<Map<String, Object>> findAllLocalizationByProjectIdAndLanguageId(Long project, Long language);

    Map<String, Object> findLocalization(Long projectId, Long languageId);
}
