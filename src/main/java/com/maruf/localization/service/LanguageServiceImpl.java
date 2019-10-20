package com.maruf.localization.service;

import com.maruf.localization.entity.Language;
import com.maruf.localization.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service(value = "languageService")
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

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
    public Language findById(String languageId) {
        return languageRepository
                .findByLanguageId(languageId)
                .orElseThrow(() -> new EntityNotFoundException("language not found for the id: " + languageId));
    }

    @Override
    public List<Language> findAll(String projectId) {
        return languageRepository.findByProjectId(projectId);
    }

    @Override
    public void delete(String languageId) {
        languageRepository.deleteById(languageId);
    }


    @Override
    public List<Language> findLanguageByProject(String projectName) {
        return languageRepository.findByProjectName(projectName);
    }
}
