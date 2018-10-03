package com.maruf.i18n.language.repository;

import com.maruf.i18n.language.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, String> {

    @Query("SELECT l from Language l " +
            " LEFT JOIN FETCH l.project " +
            " WHERE l.tenant=?1 " +
            " AND " +
            " l.id=?2")
    Optional<Language> findById(String tenant, String languageId);

    List<Language> findByProjectId(String projectId);

    List<Language> findByProjectName(String projectName);

    @Query("SELECT l FROM Language l WHERE l.code=?1")
    Optional<Language> findByCode(String code);


}
