package com.maruf.localization.service;

import com.maruf.localization.entity.Localization;
import com.maruf.localization.repository.LocalizationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "localizationService")
public class LocalizationServiceImpl implements LocalizationService {

    private final LocalizationRepository localizationRepository;
    public LocalizationServiceImpl(LocalizationRepository localizationRepository) {
        this.localizationRepository = localizationRepository;
    }

    @Override
    public Localization create(Localization localization) {
        return localizationRepository.save(localization);
    }

    @Override
    public Localization update(Localization localization) {
        Optional<Localization> found = localizationRepository.findById(localization.getId());
        if(!found.isPresent()){
            throw new IllegalArgumentException("Localization is not found");
        }

        Localization existingLocalization = found.get();
        existingLocalization.setLangKey(localization.getLangKey());
        existingLocalization.setLanguage(existingLocalization.getLanguage());
        existingLocalization.setValue(existingLocalization.getValue());
        return localizationRepository.save(existingLocalization);
    }

    @Override
    public Localization findById(Long localizationId) {
        return null;
    }

    @Override
    public Page<Localization> findByLanguageId(Long languageId) {
        return null;
    }

    @Override
    public Page<Localization> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void delete(Long localizationId) {

    }
}
