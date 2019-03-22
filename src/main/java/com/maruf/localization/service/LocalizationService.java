package com.maruf.localization.service;

import com.maruf.localization.api.dto.LocalizationDto;
import com.maruf.localization.entity.LocalizationKey;

import java.util.List;
import java.util.Map;

public interface LocalizationService {

    LocalizationDto create(LocalizationDto localizationDto);

    LocalizationDto update(LocalizationDto localizationDto);

    void importToLocalization(List<LocalizationDto> localizationDtoList);

    List<Map<String, Object>> findAll(String languageId);

    LocalizationDto findOne(String localization, String languageId);

    void delete(String localizationId);

    Map<String, Object> findLocalizationByProjectNameAndLanguageCode(String projectName, String languageCode);

    Map<String, Object> findLocalizationByProjectIdAndLanguageCode(String languageId);

    List<String> findLocalizationKeyByProject(String projectName);

    LocalizationKey findLocalizationValueByKey(String projectName, String key);
}
