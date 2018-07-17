package com.maruf.localization.repository;

import com.maruf.localization.entity.Language;
import com.maruf.localization.entity.Localization;
import com.maruf.localization.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocalizationRepository extends JpaRepository<Localization, Long> {

    Localization findByLangKeyAndProjectId(String langKey, Long projectId);

    @Query("SELECT l FROM Localization l " +
            "left join l.localizationValues lv ON (lv.language = ?2 and lv.localization = l) " +
            "WHERE l.project = ?1")
    Page<Localization> findByProjectId(Project project, Language language,  Pageable pageable);


}
