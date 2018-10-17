package com.maruf.i18n.localization.repository;

import com.maruf.i18n.language.entity.Language;
import com.maruf.i18n.localization.entity.LocalizationKey;
import com.maruf.i18n.localization.entity.LocalizationValue;
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

