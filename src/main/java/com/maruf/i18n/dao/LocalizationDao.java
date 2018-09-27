package com.maruf.i18n.dao;

import java.util.List;
import java.util.Map;

public interface LocalizationDao {

    List<Map<String, Object>> findAllLocalizationBylanguage(Long project, Long language);
}
