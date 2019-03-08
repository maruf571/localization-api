package com.maruf.i18n.client;

import com.maruf.i18n.entity.Language;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "localization-api")
public interface LanguageProxy {

    @PostMapping("/api/protected/languages")
    Language create(@RequestBody Language language);

    @PutMapping("/api/protected/languages")
    Language update(@RequestBody Language language);

    @GetMapping("/api/protected/languages/{languageId}")
    Language findById(@PathVariable String languageId);

    @GetMapping("/api/protected/languages/projects/{projectId}")
    List<Language> findAll(@PathVariable String projectId);

    @DeleteMapping("/api/protected/languages/{languageId}")
    void delete(@PathVariable String languageId);
}
