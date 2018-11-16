package com.maruf.i18n.service;

import com.maruf.i18n.entity.Language;
import com.maruf.i18n.repository.LanguageRepository;
import com.maruf.i18n.dao.LocalizationDao;
import com.maruf.i18n.api.dto.LocalizationDto;
import com.maruf.i18n.entity.LocalizationKey;
import com.maruf.i18n.entity.LocalizationValue;
import com.maruf.i18n.repository.LocalizationKeyRepository;
import com.maruf.i18n.repository.LocalizationValueRepository;
import com.maruf.i18n.entity.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public LocalizationServiceImpl(LocalizationKeyRepository localizationKeyRepository,
                                   LocalizationValueRepository localizationValueRepository,
                                   LanguageRepository languageRepository,
                                   LocalizationDao localizationDao) {
        this.localizationValueRepository = localizationValueRepository;
        this.localizationKeyRepository = localizationKeyRepository;
        this.languageRepository = languageRepository;
        this.localizationDao = localizationDao;
    }


    /**
     * This method will return all the key/value pair in public api, so no tenant here
     *
     *  @param projectName
     * @param languageCode
     * @return
     */
    @Override
    public Map<String, Object> findLocalizationByProjectNameAndLanguageCode(String projectName, String languageCode) {
        log.debug("projectName: {}, languageCode: {}", projectName, languageCode);

        Language language = languageRepository.findByProjectNameAndLanguageCode(
                projectName,
                languageCode
        ).orElseThrow(() -> new EntityNotFoundException("language not found"));

        return  localizationDao.findLocalization(language.getProject().getId(), language.getId());
    }


    /**
     * This method will return all the key/value pair in public api, so no tenant here
     *
     * @param languageId
     * @return
     */
    @Override
    public Map<String, Object> findLocalizationByProjectIdAndLanguageCode(String languageId) {
        log.debug("languageCode: {}", languageId);

        Language language = languageRepository.findByLanguageId(languageId).orElseThrow(() -> new EntityNotFoundException("language not found"));

        return  localizationDao.findLocalization(language.getProject().getId(), language.getId());
    }


    /**
     * This method will return all the localization keys and if there are any value for a specific language.
     *
     * @param languageId
     * @return
     */
    @Override
    public List<Map<String, Object>> findAll( String languageId) {
        log.debug("languageId: {}", languageId);

        Language language = languageRepository.findByLanguageId(languageId).orElseThrow(() -> new EntityNotFoundException("language not found"));

        return  localizationDao.findAllLocalizationByProjectIdAndLanguageId(language.getProject().getId(), language.getId());
    }


    /**
     * This method will return localization key and if there is any value
     *
     * @param localizationId
     * @param languageId
     * @return
     */
    @Override
    @Transactional
    public LocalizationDto findOne(String localizationId, String languageId) {
        log.debug("localizationId: {}", localizationId);
        log.debug("languageId: {}", languageId);

        LocalizationKey lk = localizationKeyRepository.findByLocalizationId(localizationId).orElseThrow(EntityNotFoundException::new);
        String value = "";
        if(lk.getLocalizationValues() != null) {
            for (LocalizationValue lv : lk.getLocalizationValues()) {
                if (lv.getLanguage().getId().equals(languageId)) {
                    value = lv.getValue();
                }
            }
        }

        return LocalizationDto.builder()
                .id(lk.getId())
                .langKey(lk.getLangKey())
                .value(value)
                .languageId(languageId)
                .build();
    }

    /**
     * create new localization key with or without value
     *
     * @param localizationDto
     * @return
     */
    @Override
    @Transactional
    public LocalizationDto create(LocalizationDto localizationDto) {
        Language language = languageRepository.findByLanguageId(localizationDto.getLanguageId()).orElseThrow(()-> new EntityNotFoundException("language not found"));

        // Create localization key
        LocalizationKey localizationKey = new LocalizationKey();
        localizationKey.setLangKey(localizationDto.getLangKey());
        localizationKey.setProject(language.getProject());
        localizationKeyRepository.save(localizationKey);

        // Create localization value
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


    /**
     * Update a localization key with or without value
     *
     * @param localizationDto
     * @return
     */
    @Override
    @Transactional
    public LocalizationDto update(LocalizationDto localizationDto) {
        LocalizationKey found = localizationKeyRepository.findByLocalizationId(localizationDto.getId()).orElseThrow(() -> new EntityNotFoundException("localizationKey not found"));
        Language language = languageRepository.findByLanguageId(localizationDto.getLanguageId()).orElseThrow(() -> new EntityNotFoundException("language not found"));

        //update key
        if(!found.getLangKey().equals(localizationDto.getLangKey())){
            found.setLangKey(localizationDto.getLangKey());
            localizationKeyRepository.save(found);
        }

        //update value
        LocalizationValue localizationValue = localizationValueRepository.findByLanguageAndLocalizationKey(language, found);
        if(localizationValue == null) {
            localizationValue = new LocalizationValue();
            localizationValue.setLocalizationKey(found);
            localizationValue.setLanguage(language);
        }
        localizationValue.setValue(localizationDto.getValue());
        localizationValueRepository.save(localizationValue);

        return LocalizationDto
                .builder()
                .id(found.getId())
                .langKey(found.getLangKey())
                .value(localizationValue.getValue())
                .languageId(language.getId())
                .build();
    }




    /**
     * Delete localization key
     *
     * @param localizationId
     */
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
        return null; //localizationKeyRepository.findByProjectNameAndKey(projectName, key);
    }


    /**
     * import from excel file to localization table
     *
     * @param localizationDtoList
     */
    @Override
    public void importToLocalization(List<LocalizationDto> localizationDtoList) {

        //check if key exist or not
        Language language = languageRepository.findByLanguageId(localizationDtoList.get(0).getLanguageId()).orElseThrow(EntityNotFoundException::new);
        Project project = language.getProject();

        for(LocalizationDto localizationDto: localizationDtoList) {
            Optional<LocalizationKey> localizationKey = localizationKeyRepository.findByProjectIdAndKey(
                    project.getId(),
                    localizationDto.getLangKey()
            );
            if (localizationKey.isPresent()) {
                LocalizationValue localizationValue = localizationValueRepository.findByLanguageAndLocalizationKey(language, localizationKey.get());
                if(localizationValue == null){
                    localizationValue = new LocalizationValue();
                    localizationValue.setLanguage(language);
                    localizationValue.setLocalizationKey(localizationKey.get());
                }
                localizationValue.setValue(localizationDto.getValue());
                localizationValueRepository.save(localizationValue);

            } else {

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
