package com.maruf.i18n.language.service;

import com.maruf.i18n.language.entity.Language;
import com.maruf.i18n.language.repository.LanguageRepository;
import com.maruf.i18n.tenant.TenantContext;
import com.maruf.i18n.tenant.entity.Tenant;
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
        language.setTenant(TenantContext.getCurrentTenant());
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
                .findById(
                        TenantContext.getCurrentTenant(),
                        languageId
                )
                .orElseThrow(() -> new EntityNotFoundException("language not found for the id " + languageId));
    }

    @Override
    public List<Language> findAll(String projectId) {
        return languageRepository.findByProjectId(
                TenantContext.getCurrentTenant(),
                projectId
        );
    }

    @Override
    public void delete(String languageId) {
        languageRepository.deleteById(languageId);
    }


    @Override
    public List<Language> findLanguageByProject(String projectName) {
        return languageRepository.findByProjectName(
                TenantContext.getCurrentTenant(),
                projectName
        );
    }
}
