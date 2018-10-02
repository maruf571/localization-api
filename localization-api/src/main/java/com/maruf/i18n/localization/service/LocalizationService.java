package com.maruf.i18n.localization.service;

import com.maruf.i18n.localization.dto.LocalizationDto;
import com.maruf.i18n.localization.entity.LocalizationKey;

import java.util.List;
import java.util.Map;

public interface LocalizationService {

    LocalizationDto create(LocalizationDto localizationDto);

    LocalizationDto update(LocalizationDto localizationDto);

    void importToLocalization(List<LocalizationDto> localizationDtoList);

    List<Map<String, Object>> findAll(Long projectId, Long languageId);

    LocalizationDto findOne(Long projectId, Long languageId, Long localization);

    void delete(Long localizationId);

    Map<String, Object> findLocalizationByProjectAndLanguageCode(String projectName, String languageCode);

    Map<String, Object> findLocalizationByProjectAndLanguageCode(Long  projectId, Long languageId);

    List<String> findLocalizationKeyByProject(String projectName);

    LocalizationKey findLocalizationValueByKey(String projectName, String key);
}
