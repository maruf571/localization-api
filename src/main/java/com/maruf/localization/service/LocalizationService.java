package com.maruf.localization.service;

import com.maruf.localization.entity.Language;
import com.maruf.localization.entity.Localization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocalizationService {

    Localization create(Localization localization);

    Localization update(Localization localization);

    Localization findById(Long localizationId);

    Page<Localization> findByLanguageId(Long languageId);

    Page<Localization> findAll(Pageable pageable);

    void delete(Long localizationId);
}
