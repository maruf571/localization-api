package com.maruf.localization.repository;

import com.maruf.localization.entity.Language;
import com.maruf.localization.entity.Localization;
import com.maruf.localization.entity.LocalizationValue;
import com.maruf.localization.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocalizationValueRepository extends JpaRepository<LocalizationValue, Long> {

    LocalizationValue findByLanguageAndLocalization(Language language, Localization localization);
}

