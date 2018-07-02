package com.maruf.localization.service;

import com.maruf.localization.entity.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LanguageService {

    Language create(Language language);

    Language update(Language language);

    Language findById(Long languageId);

    Language findByLangCode(String langCode);

    Page<Language> findAll(Pageable pageable);

    void delete(Long languageId);
}
