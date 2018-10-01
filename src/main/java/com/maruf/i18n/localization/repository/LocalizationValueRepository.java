package com.maruf.i18n.localization.repository;

import com.maruf.i18n.language.entity.Language;
import com.maruf.i18n.localization.entity.LocalizationKey;
import com.maruf.i18n.localization.entity.LocalizationValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizationValueRepository extends JpaRepository<LocalizationValue, Long> {

    LocalizationValue findByLanguageAndLocalizationKey(Language language, LocalizationKey localizationKey);
}

