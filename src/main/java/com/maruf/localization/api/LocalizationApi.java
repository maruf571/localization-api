package com.maruf.localization.api;

import com.maruf.localization.dto.LocalizationDto;
import com.maruf.localization.service.LocalizationService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/localizations")
public class LocalizationApi {

    private final LocalizationService localizationService;
    public LocalizationApi(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @GetMapping("/project/{projectId}/language/{languageId}")
    public ResponseEntity<List<Map<String, Object>>> findAll(@PathVariable Long projectId, @PathVariable Long languageId){
        return ResponseEntity.ok()
                .body(localizationService.findAll(projectId, languageId));
    }

    @PostMapping
    public ResponseEntity<LocalizationDto> create(@RequestBody LocalizationDto localizationDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(localizationService.create(localizationDto));
    }

    @PutMapping
    public ResponseEntity<LocalizationDto> update(@RequestBody LocalizationDto localizationDto){
        return ResponseEntity.ok()
                .body(localizationService.update(localizationDto));
    }

    @DeleteMapping("/{localizationId}")
    public void delete(@PathVariable Long localizationId){
        localizationService.delete(localizationId);
    }
}
