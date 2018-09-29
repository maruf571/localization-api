package com.maruf.i18n.service;

import com.maruf.i18n.dto.LocalizationDto;
import com.maruf.i18n.entity.LocalizationKey;

import java.util.List;
import java.util.Map;

public interface LocalizationService {

    LocalizationDto create(LocalizationDto localizationDto);

    LocalizationDto update(LocalizationDto localizationDto);

    void importToLocalization(List<LocalizationDto> localizationDtoList);

    List<Map<String, Object>> findAll(Long projectId, Long languageId);

    void delete(Long localizationId);

    Map<String, Object> findLocalizationByProjectAndLanguageCode(String projectName, String languageCode);

    Map<String, Object> findLocalizationByProjectAndLanguageCode(Long  projectId, Long languageId);

    List<String> findLocalizationKeyByProject(String projectName);

    LocalizationKey findLocalizationValueByKey(String projectName, String key);
}
