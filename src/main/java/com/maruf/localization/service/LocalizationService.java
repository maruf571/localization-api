package com.maruf.localization.service;

import com.maruf.localization.dto.LocalizationDto;
import com.maruf.localization.entity.Localization;
import com.maruf.localization.entity.LocalizationValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface LocalizationService {

    LocalizationDto create(LocalizationDto localizationDto);

    LocalizationDto update(LocalizationDto localizationDto);

    List<Map<String, Object>> findAll(Long projectId, Long languageId);

    void delete(Long localizationId);
}
