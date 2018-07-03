package com.maruf.localization.service;

import com.maruf.localization.entity.Language;
import com.maruf.localization.repository.LanguageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "languageService")
public class LanguageServiceImpl implements LanguageService{

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
        Optional<Language> found = languageRepository.findById(language.getId());
        if(!found.isPresent()){
            //throw exception
        }

        Language existingLanguage = found.get();
        existingLanguage.setIsActive(language.getIsActive());
        existingLanguage.setIsRtl(language.getIsRtl());
        existingLanguage.setCode(language.getCode());
        existingLanguage.setName(language.getName());
        return languageRepository.save(existingLanguage);
    }

    @Override
    public Language findById(Long languageId) {
        //TODO: use java8 syntax
        Optional<Language> found = languageRepository.findById(languageId);
        if(!found.isPresent()){
            //throw exception
        }
        return found.get();
    }

    @Override
    public Language findByLangCode(String langCode) {
        return null;
    }

    @Override
    public Page<Language> findAll(Pageable pageable) {
        return languageRepository.findAll(pageable);
    }

    @Override
    public void delete(Long languageId) {

    }
}
