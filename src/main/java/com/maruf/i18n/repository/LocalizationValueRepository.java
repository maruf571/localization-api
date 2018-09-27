package com.maruf.i18n.repository;

import com.maruf.i18n.entity.Language;
import com.maruf.i18n.entity.LocalizationKey;
import com.maruf.i18n.entity.LocalizationValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizationValueRepository extends JpaRepository<LocalizationValue, Long> {

    LocalizationValue findByLanguageAndLocalizationKey(Language language, LocalizationKey localizationKey);
}

