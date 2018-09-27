package com.maruf.i18n.api;

import com.maruf.i18n.service.LanguageService;
import com.maruf.i18n.service.LocalizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project/")
public class LocalizationPublicApi {

    private final LanguageService languageService;
    private final LocalizationService localizationService;
    public LocalizationPublicApi(LanguageService languageService,
                                 LocalizationService localizationService) {
        this.languageService = languageService;
        this.localizationService = localizationService;
    }

    @GetMapping("/{projectName}/languages")
    public ResponseEntity getAllLanguage(@PathVariable String projectName){

        return ResponseEntity.ok().body(
                languageService.findLanguageByProject(projectName)
        );
    }


    @GetMapping("/{projectName}/languages/{languageCode}")
    public ResponseEntity getAllLocalization(@PathVariable String projectName, @PathVariable String languageCode){

        return ResponseEntity.ok().body(
                localizationService.findLocalizationByProjectAndLanguageCode(projectName, languageCode)
        );
    }


    @GetMapping("/{projectName}/language-keys")
    public ResponseEntity getAllKeys(@PathVariable String projectName){

        return ResponseEntity.ok().body(
                localizationService.findLocalizationKeyByProject(projectName)
        );
    }

    @GetMapping("/{projectName}/language-keys/{key}")
    public ResponseEntity getAllLocalizationByKey(@PathVariable String projectName, @PathVariable String key){

        return ResponseEntity.ok().body(
                localizationService.findLocalizationValueByKey(projectName, key)
        );
    }
}
