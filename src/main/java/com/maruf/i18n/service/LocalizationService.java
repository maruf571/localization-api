package com.maruf.i18n.service;

import com.maruf.i18n.dto.LocalizationDto;
import com.maruf.i18n.entity.LocalizationKey;

import java.util.List;
import java.util.Map;

public interface LocalizationService {

    LocalizationDto create(LocalizationDto localizationDto);

    LocalizationDto update(LocalizationDto localizationDto);

    List<Map<String, Object>> findAll(Long projectId, Long languageId);

    void delete(Long localizationId);

    List<Map<String, Object>> findLocalizationByProjectAndLanguageCode(String projectName, String languageCode);

    List<String> findLocalizationKeyByProject(String projectName);

    LocalizationKey findLocalizationValueByKey(String projectName, String key);
}
