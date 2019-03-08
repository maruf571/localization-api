package com.maruf.i18n.client;

import com.maruf.i18n.api.dto.LocalizationDto;
import com.maruf.i18n.entity.Language;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@FeignClient(name = "localization-api")
public interface LocalizationProxy {

    @PostMapping("/api/protected/localizations")
    LocalizationDto create(@RequestBody LocalizationDto localizationDto);

    @PutMapping("/api/protected/localizations")
    LocalizationDto update(@RequestBody LocalizationDto localizationDto);

    @PutMapping("/api/protected/localizations/{localizationId}/language/{languageId}")
    LocalizationDto findById(@PathVariable String localizationId, @PathVariable String languageId);

    @GetMapping("/api/protected/localizations/language/{languageId}")
    List<Map<String, Object>> findAll(@PathVariable String languageId);

    @DeleteMapping("/api/protected/localizations/{localizationId}")
    void delete(@PathVariable String localizationId);

    @PostMapping("/language/{languageId}/import")
    List<LocalizationDto> importLocalization(@PathVariable String languageId, @RequestParam("file") MultipartFile file);

}
