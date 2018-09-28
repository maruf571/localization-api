package com.maruf.i18n.api;

import com.maruf.i18n.service.LanguageService;
import com.maruf.i18n.service.LocalizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LocalizationPublicApi {

    private final LanguageService languageService;
    private final LocalizationService localizationService;
    public LocalizationPublicApi(LanguageService languageService,
                                 LocalizationService localizationService) {
        this.languageService = languageService;
        this.localizationService = localizationService;
    }

    @GetMapping("/api/project-name/{projectName}/language-code/{languageCode}")
    public ResponseEntity getAllLocalization(@PathVariable String projectName, @PathVariable String languageCode){
        log.debug("projectName: {}, languageCode: {}", projectName, languageCode);
        return ResponseEntity.ok().body(
                localizationService.findLocalizationByProjectAndLanguageCode(projectName, languageCode)
        );
    }

    @GetMapping("/api/project-id/{projectId}/language-id/{languageId}")
    public ResponseEntity getAllLocalization(@PathVariable Long projectId, @PathVariable Long languageId){
        log.debug("projectId: {}, languageId: {}", projectId, languageId);
        return ResponseEntity.ok().body(
                localizationService.findLocalizationByProjectAndLanguageCode(projectId, languageId)
        );
    }


    @GetMapping("/api/project-name/{projectName}/languages")
    public ResponseEntity getAllLanguage(@PathVariable String projectName){
        log.debug("projectName: {}", projectName);
        return ResponseEntity.ok().body(
                languageService.findLanguageByProject(projectName)
        );
    }

    @GetMapping("/api/project-name/{projectName}/language-keys")
    public ResponseEntity getAllKeys(@PathVariable String projectName){
        log.debug("projectName: {}", projectName);
        return ResponseEntity.ok().body(
                localizationService.findLocalizationKeyByProject(projectName)
        );
    }

    @GetMapping("/api/project-name/{projectName}/language-keys/{languageKey}")
    public ResponseEntity getAllLocalizationByKey(@PathVariable String projectName, @PathVariable String languageKey){
        log.debug("projectName: {}, languageKey: {}", projectName, languageKey);
        return ResponseEntity.ok().body(
                localizationService.findLocalizationValueByKey(projectName, languageKey)
        );
    }
}
