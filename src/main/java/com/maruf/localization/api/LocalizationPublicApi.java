package com.maruf.localization.api;

import com.maruf.localization.service.LocalizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LocalizationPublicApi {

    private final LocalizationService localizationService;


    @GetMapping("/api/project-name/{projectName}/language-code/{languageCode}")
    public ResponseEntity getAllLocalizationByProjectNameAndCode(@PathVariable String projectName, @PathVariable String languageCode){
        log.debug("projectName: {}, languageCode: {}", projectName, languageCode);
        return ResponseEntity.ok().body(
                localizationService.findLocalizationByProjectNameAndLanguageCode(projectName, languageCode)
        );
    }

    @GetMapping("/api/language-id/{languageId}")
    public ResponseEntity getAllLocalizationByProjectIdAndCode(@PathVariable String languageId){
        log.debug("languageId: {}", languageId);
        return ResponseEntity.ok().body(
                localizationService.findLocalizationByProjectIdAndLanguageCode(languageId)
        );
    }

}
