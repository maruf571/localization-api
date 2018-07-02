package com.maruf.localization.api;

import com.maruf.localization.entity.Language;
import org.springframework.data.domain.Pageable;
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

@RestController
@RequestMapping("/api/languages")
public class LanguageApi {

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String name, Pageable pageable){
        return null;
    }

    @GetMapping("/{languageId}")
    public ResponseEntity findById(@PathVariable Long languageId){
        return null;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Language language){
        return null;
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Language language){
        return null;
    }

    @DeleteMapping("/{languageId}")
    public void delete(@RequestParam Long languageId){

    }

}
