package com.maruf.i18n.localization.service;

import com.maruf.i18n.language.entity.Language;
import com.maruf.i18n.language.repository.LanguageRepository;
import com.maruf.i18n.localization.dao.LocalizationDao;
import com.maruf.i18n.localization.dto.LocalizationDto;
import com.maruf.i18n.localization.entity.LocalizationKey;
import com.maruf.i18n.localization.entity.LocalizationValue;
import com.maruf.i18n.localization.repository.LocalizationKeyRepository;
import com.maruf.i18n.localization.repository.LocalizationValueRepository;
import com.maruf.i18n.project.entity.Project;
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

        Language language = languageRepository.findById(
                languageId
        ).orElseThrow(() -> new EntityNotFoundException("language not found"));

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

        Language language = languageRepository.findById(
                TenantContext.getCurrentTenant(),
                languageId
        ).orElseThrow(() -> new EntityNotFoundException("language not found"));

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
    public LocalizationDto findOne(String localizationId, String languageId) {
        log.debug("localization: {}", localizationId);

        LocalizationKey lk = localizationKeyRepository.findById(
                TenantContext.getCurrentTenant(),
                localizationId
        );
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
    public LocalizationDto create(LocalizationDto localizationDto) {

        String tenant = TenantContext.getCurrentTenant();
        Language language = languageRepository.findById(
                tenant,
                localizationDto.getLanguageId()
        ).orElseThrow(()-> new EntityNotFoundException("language not found"));

        LocalizationKey localizationKey = new LocalizationKey();
        localizationKey.setLangKey(localizationDto.getLangKey());
        localizationKey.setProject(language.getProject());
        localizationKey.setTenant(tenant);
        localizationKeyRepository.save(localizationKey);

        LocalizationValue localizationValue = new LocalizationValue();
        if(localizationDto.getValue() != null ) {
            localizationValue.setValue(localizationDto.getValue());
            localizationValue.setLocalizationKey(localizationKey);
            localizationValue.setLanguage(language);
            localizationValue.setTenant(tenant);
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
            localizationValue.setTenant(TenantContext.getCurrentTenant());
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
        Language language = languageRepository.findById(
                TenantContext.getCurrentTenant(),
                localizationDtoList.get(0).getLanguageId()
        ).orElseThrow(EntityNotFoundException::new);
        Project project = language.getProject();

        for(LocalizationDto localizationDto: localizationDtoList) {
            Optional<LocalizationKey> localizationKey = localizationKeyRepository.findByProjectIdAndKey(
                    TenantContext.getCurrentTenant(),
                    project.getId(),
                    localizationDto.getLangKey()
            );
            if (localizationKey.isPresent()) {
                //update existing value
                LocalizationValue localizationValue = localizationValueRepository.findByLanguageAndLocalizationKey(language, localizationKey.get());
                if(localizationValue == null){
                    localizationValue = new LocalizationValue();
                    localizationValue.setTenant(TenantContext.getCurrentTenant());
                    localizationValue.setLanguage(language);
                    localizationValue.setLocalizationKey(localizationKey.get());
                }
                localizationValue.setValue(localizationDto.getValue());
                localizationValueRepository.save(localizationValue);

            } else {
                //add new key and value
                LocalizationKey newKey = new LocalizationKey();
                newKey.setTenant(TenantContext.getCurrentTenant());
                newKey.setLangKey(localizationDto.getLangKey());
                newKey.setProject(project);
                localizationKeyRepository.save(newKey);

                LocalizationValue newValue = new LocalizationValue();
                newValue.setTenant(TenantContext.getCurrentTenant());
                newValue.setLanguage(language);
                newValue.setLocalizationKey(newKey);
                newValue.setValue(localizationDto.getValue());
                localizationValueRepository.save(newValue);
            }
        }
    }
}
