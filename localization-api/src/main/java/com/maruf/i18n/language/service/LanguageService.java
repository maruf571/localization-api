package com.maruf.i18n.language.service;

import com.maruf.i18n.language.entity.Language;

import java.util.List;

public interface LanguageService {

    List<Language> findAll(String projectId);

    Language findById(String languageId);

    Language create(Language language);

    Language update(Language language);

    void delete(String languageId);

    List<Language> findLanguageByProject(String projectName);
}
