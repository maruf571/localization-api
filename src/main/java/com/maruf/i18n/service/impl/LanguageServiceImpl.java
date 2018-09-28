package com.maruf.i18n.service.impl;

import com.maruf.i18n.entity.Language;
import com.maruf.i18n.repository.LanguageRepository;
import com.maruf.i18n.service.LanguageService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "languageService")
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public Language create(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Language update(Language language) {
        Language found = languageRepository
                .findById(language.getId())
                .orElseThrow(() -> new EntityNotFoundException("language not found"));

        found.setIsActive(language.getIsActive());
        found.setIsRtl(language.getIsRtl());
        found.setCode(language.getCode());
        found.setName(language.getName());
        return languageRepository.save(found);
    }

    @Override
    public Language findById(Long languageId) {
        return languageRepository
                .findById(languageId)
                .orElseThrow(() -> new EntityNotFoundException("language not found for the id " + languageId));
    }

    @Override
    public List<Language> findAll(Long projectId) {
        return languageRepository.findByProjectId(projectId);
    }

    @Override
    public void delete(Long languageId) {
        languageRepository.deleteById(languageId);
    }


    @Override
    public List<Language> findLanguageByProject(String projectName) {
        return languageRepository.findByProjectName(projectName);
    }
}
