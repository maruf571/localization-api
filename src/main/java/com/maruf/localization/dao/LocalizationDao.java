package com.maruf.localization.dao;

import com.maruf.localization.dto.LocalizationDto;
import com.maruf.localization.entity.Language;
import com.maruf.localization.entity.Localization;
import com.maruf.localization.entity.Project;

import java.util.List;
import java.util.Map;

public interface LocalizationDao {

    List<Map<String, Object>> findAllLocalizationBylanguage(Long project, Long language);
}
