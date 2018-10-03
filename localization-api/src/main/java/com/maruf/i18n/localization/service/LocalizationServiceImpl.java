package com.maruf.i18n.localization.service;

import com.maruf.i18n.localization.dao.LocalizationDao;
import com.maruf.i18n.localization.dto.LocalizationDto;
import com.maruf.i18n.language.entity.Language;
import com.maruf.i18n.localization.entity.LocalizationKey;
import com.maruf.i18n.localization.entity.LocalizationValue;
import com.maruf.i18n.project.entity.Project;
import com.maruf.i18n.language.repository.LanguageRepository;
import com.maruf.i18n.localization.repository.LocalizationKeyRepository;
import com.maruf.i18n.localization.repository.LocalizationValueRepository;
import com.maruf.i18n.project.repository.ProjectRepository;
import com.maruf.i18n.tenant.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public List<Map<String, Object>> findAll(String projectId, String languageId) {
        return  localizationDao.findAllLocalizationByProjectIdAndLanguageId(projectId, languageId);
    }

    @Override
    public Map<String, Object> findLocalizationByProjectNameAndLanguageCode(String projectName, String languageCode) {
        log.debug("projectName: {}, languageCode: {}", projectName, languageCode);

        Language language = languageRepository.findByCode(languageCode).orElseThrow(() -> new EntityNotFoundException("language not found"));
        Project project = projectRepository.findByName(TenantContext.getCurrentTenant(), projectName).orElseThrow(() ->new EntityNotFoundException("Project name not found"));
        return findLocalizationByProjectIdAndLanguageCode(project.getId(), language.getId());
    }


    @Override
    public Map<String, Object> findLocalizationByProjectIdAndLanguageCode(String  projectId, String languageId) {
        log.debug("projectName: {}, languageCode: {}", projectId, languageId);
         return  localizationDao.findLocalization(projectId, languageId);
    }

    @Override
    public LocalizationDto findOne(String projectId, String languageId, String localizationId) {
        log.debug("projectId: {}, languageId: {}, localization: {}", projectId, languageId, localizationId);

        LocalizationKey lk = localizationKeyRepository.findByProjectNameAndKey(projectId, localizationId);
        String value = "";
        for(LocalizationValue lv : lk.getLocalizationValues()){
            if(lv.getLanguage().getId().equals(languageId)){
                value = lv.getValue();
            }
        }
        return LocalizationDto.builder()
                .id(lk.getId())
                .langKey(lk.getLangKey())
                .value(value)
                .projectId(projectId)
                .languageId(languageId)
                .build();
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


        return LocalizationDto.builder()
                .id(localizationKey.getId())
                .langKey(localizationKey.getLangKey())
                .value(localizationValue.getValue())
                .languageId(language.getId())
                .build();
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

        return LocalizationDto
                .builder()
                .id(localizationKey.getId())
                .langKey(localizationKey.getLangKey())
                .value(localizationValue.getValue())
                .languageId(language.getId())
                .build();
    }

    @Override
    public void delete(String localizationId) {
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

    @Override
    public void importToLocalization(List<LocalizationDto> localizationDtoList) {

        //cehck if key exist or not
        Project project = projectRepository.findById(localizationDtoList.get(0).getProjectId()).orElseThrow(EntityNotFoundException::new);
        Language language = languageRepository.findById(localizationDtoList.get(0).getLanguageId()).orElseThrow(EntityNotFoundException::new);

        for(LocalizationDto localizationDto: localizationDtoList) {
            Optional<LocalizationKey> localizationKey = localizationKeyRepository.findByProjectIdAndKey(localizationDto.getProjectId(), localizationDto.getLangKey());
            if (localizationKey.isPresent()) {
                //update existing value
                LocalizationValue localizationValue = localizationValueRepository.findByLanguageAndLocalizationKey(language, localizationKey.get());
                if(localizationValue == null){
                    localizationValue = new LocalizationValue();
                    localizationValue.setLanguage(language);
                    localizationValue.setLocalizationKey(localizationKey.get());
                }
                localizationValue.setValue(localizationDto.getValue());
                localizationValueRepository.save(localizationValue);

            } else {
                //add new key and value
                LocalizationKey newKey = new LocalizationKey();
                newKey.setLangKey(localizationDto.getLangKey());
                newKey.setProject(project);
                localizationKeyRepository.save(newKey);

                LocalizationValue newValue = new LocalizationValue();
                newValue.setLanguage(language);
                newValue.setLocalizationKey(newKey);
                newValue.setValue(localizationDto.getValue());
                localizationValueRepository.save(newValue);
            }
        }
    }
}
