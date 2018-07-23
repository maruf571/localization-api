package com.maruf.localization.service;

import com.maruf.localization.entity.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LanguageService {

    List<Language> findAll(Long projectId);

    Language findById(Long languageId);

    Language findByLangCode(String langCode);

    Language create(Language language);

    Language update(Language language);

    void delete(Long languageId);
}
