package com.maruf.localization.service.impl;

import com.maruf.localization.dao.LocalizationDao;
import com.maruf.localization.dto.LocalizationDto;
import com.maruf.localization.entity.Language;
import com.maruf.localization.entity.Localization;
import com.maruf.localization.entity.LocalizationValue;
import com.maruf.localization.repository.LanguageRepository;
import com.maruf.localization.repository.LocalizationRepository;
import com.maruf.localization.repository.LocalizationValueRepository;
import com.maruf.localization.service.LocalizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service(value = "localizationService")
public class LocalizationServiceImpl implements LocalizationService {

    private final LocalizationRepository localizationRepository;
    private final LocalizationValueRepository localizationValueRepository;
    private final LanguageRepository languageRepository;
    private final LocalizationDao localizationDao;
    public LocalizationServiceImpl(LocalizationRepository localizationRepository,
                                   LocalizationValueRepository localizationValueRepository,
                                   LanguageRepository languageRepository,
                                   LocalizationDao localizationDao) {
        this.localizationValueRepository = localizationValueRepository;
        this.localizationRepository = localizationRepository;
        this.languageRepository = languageRepository;
        this.localizationDao = localizationDao;
    }


    @Override
    public List<Map<String, Object>> findAll(Long projectId, Long languageId) {
        return  localizationDao.findAllLocalizationBylanguage(projectId, languageId);
    }

    @Override
    public LocalizationDto create(LocalizationDto localizationDto) {

        Language language = languageRepository.findById(localizationDto.getLanguageId()).orElseThrow(()-> new EntityNotFoundException("language not found"));

        Localization localization = new Localization();
        localization.setLangKey(localizationDto.getLangKey());
        localization.setProject(language.getProject());
        localizationRepository.save(localization);

        LocalizationValue localizationValue = new LocalizationValue();
        if(localizationDto.getValue() != null ) {
            localizationValue.setValue(localizationDto.getValue());
            localizationValue.setLocalization(localization);
            localizationValue.setLanguage(language);
            localizationValueRepository.save(localizationValue);
        }

        return new LocalizationDto(localization.getId(), language.getId(), localization.getLangKey(), localizationValue.getValue());

    }

    @Override
    public LocalizationDto update(LocalizationDto localizationDto) {
        Localization localization = localizationRepository.findById(localizationDto.getId()).orElseThrow(() -> new EntityNotFoundException("localization not found"));
        Language language = languageRepository.findById(localizationDto.getLanguageId()).orElseThrow(() -> new EntityNotFoundException("language not found"));

        //update key
        if(!localization.getLangKey().equals(localizationDto.getLangKey())){
            localization.setLangKey(localizationDto.getLangKey());
            localizationRepository.save(localization);
        }

        //update value
        LocalizationValue localizationValue = localizationValueRepository.findByLanguageAndLocalization(language, localization);
        if(localizationValue == null) {
            localizationValue = new LocalizationValue();
        }
        localizationValue.setLocalization(localization);
        localizationValue.setLanguage(language);
        localizationValue.setValue(localizationDto.getValue());
        localizationValueRepository.save(localizationValue);

        return new LocalizationDto(localization.getId(), language.getId(), localization.getLangKey(), localizationValue.getValue());

    }


    @Override
    public void delete(Long localizationId) {
        localizationRepository.deleteById(localizationId);
    }
}
