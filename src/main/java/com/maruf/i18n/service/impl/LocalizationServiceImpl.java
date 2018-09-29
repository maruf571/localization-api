package com.maruf.i18n.service.impl;

import com.maruf.i18n.dao.LocalizationDao;
import com.maruf.i18n.dto.LocalizationDto;
import com.maruf.i18n.entity.Language;
import com.maruf.i18n.entity.LocalizationKey;
import com.maruf.i18n.entity.LocalizationValue;
import com.maruf.i18n.entity.Project;
import com.maruf.i18n.repository.LanguageRepository;
import com.maruf.i18n.repository.LocalizationKeyRepository;
import com.maruf.i18n.repository.LocalizationValueRepository;
import com.maruf.i18n.repository.ProjectRepository;
import com.maruf.i18n.service.LocalizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service(value = "localizationService")
public class LocalizationServiceImpl implements LocalizationService {

    private final LocalizationKeyRepository localizationKeyRepository;
    private final LocalizationValueRepository localizationValueRepository;
    private final LanguageRepository languageRepository;
    private final LocalizationDao localizationDao;
    private final ProjectRepository projectRepository;
    public LocalizationServiceImpl(LocalizationKeyRepository localizationKeyRepository,
                                   LocalizationValueRepository localizationValueRepository,
                                   LanguageRepository languageRepository,
                                   LocalizationDao localizationDao,
                                   ProjectRepository projectRepository) {
        this.localizationValueRepository = localizationValueRepository;
        this.localizationKeyRepository = localizationKeyRepository;
        this.languageRepository = languageRepository;
        this.localizationDao = localizationDao;
        this.projectRepository = projectRepository;
    }


    @Override
    public List<Map<String, Object>> findAll(Long projectId, Long languageId) {
        return  localizationDao.findAllLocalizationByProjectIdAndLanguageId(projectId, languageId);
    }

    @Override
    public Map<String, Object> findLocalizationByProjectAndLanguageCode(String projectName, String languageCode) {
        log.debug("projectName: {}, languageCode: {}", projectName, languageCode);

        Language language = languageRepository.findByCode(languageCode).orElseThrow(() -> new EntityNotFoundException("language not found"));
        Project project = projectRepository.findByName(projectName).orElseThrow(() ->new EntityNotFoundException("Project name not found"));
        return findLocalizationByProjectAndLanguageCode(project.getId(), language.getId());
    }


    @Override
    public Map<String, Object> findLocalizationByProjectAndLanguageCode(Long  projectId, Long languageId) {
        log.debug("projectName: {}, languageCode: {}", projectId, languageId);
         return  localizationDao.findLocalization(projectId, languageId);
    }

    @Override
    public LocalizationDto create(LocalizationDto localizationDto) {

        Language language = languageRepository.findById(localizationDto.getLanguageId()).orElseThrow(()-> new EntityNotFoundException("language not found"));

        LocalizationKey localizationKey = new LocalizationKey();
        localizationKey.setLangKey(localizationDto.getLangKey());
        localizationKey.setProject(language.getProject());
        localizationKeyRepository.save(localizationKey);

        LocalizationValue localizationValue = new LocalizationValue();
        if(localizationDto.getValue() != null ) {
            localizationValue.setValue(localizationDto.getValue());
            localizationValue.setLocalizationKey(localizationKey);
            localizationValue.setLanguage(language);
            localizationValueRepository.save(localizationValue);
        }

        return new LocalizationDto(localizationKey.getId(), language.getId(), localizationKey.getLangKey(), localizationValue.getValue());

    }

    @Override
    public LocalizationDto update(LocalizationDto localizationDto) {
        LocalizationKey localizationKey = localizationKeyRepository.findById(localizationDto.getId()).orElseThrow(() -> new EntityNotFoundException("localizationKey not found"));
        Language language = languageRepository.findById(localizationDto.getLanguageId()).orElseThrow(() -> new EntityNotFoundException("language not found"));

        //update key
        if(!localizationKey.getLangKey().equals(localizationDto.getLangKey())){
            localizationKey.setLangKey(localizationDto.getLangKey());
            localizationKeyRepository.save(localizationKey);
        }

        //update value
        LocalizationValue localizationValue = localizationValueRepository.findByLanguageAndLocalizationKey(language, localizationKey);
        if(localizationValue == null) {
            localizationValue = new LocalizationValue();
        }
        localizationValue.setLocalizationKey(localizationKey);
        localizationValue.setLanguage(language);
        localizationValue.setValue(localizationDto.getValue());
        localizationValueRepository.save(localizationValue);

        return new LocalizationDto(localizationKey.getId(), language.getId(), localizationKey.getLangKey(), localizationValue.getValue());
    }

    @Override
    public void delete(Long localizationId) {
        log.debug("localizationId: {}", localizationId);
        localizationKeyRepository.deleteById(localizationId);
    }


    @Override
    public List<String> findLocalizationKeyByProject(String projectName) {
        log.debug("projectName: {}", projectName);
        return localizationKeyRepository.getAllKeysByProject(projectName);
    }

    @Override
    public LocalizationKey findLocalizationValueByKey(String projectName, String key) {
        log.debug("projectName: {}, key: {}", projectName, key);
        return localizationKeyRepository.findByProjectNameAndKey(projectName, key);
    }

}
