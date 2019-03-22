package com.maruf.localization.repository;

import com.maruf.localization.entity.Language;
import com.maruf.localization.entity.LocalizationKey;
import com.maruf.localization.entity.LocalizationValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizationValueRepository extends JpaRepository<LocalizationValue, String> {

    /*@Query("SELECT lv LocalizationValue lv " +
            " WHERE " +
            " lv.tenant=?1 " +
            " AND " +
            " lv.language.id=?2  " +
            " AND " +
            " lv.localizationKey.id=?3 ")
    LocalizationValue findByLanguageIdAndLocalizationKeyId(String tenant, String languageId, String localizationKeyId);
*/
    LocalizationValue findByLanguageAndLocalizationKey(Language language, LocalizationKey localizationKey);
}

