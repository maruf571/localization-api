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


    @Query("SELECT l from Language l " +
            " LEFT JOIN FETCH l.project lp " +
            " WHERE l.tenant=?1 " +
            " AND " +
            " lp.id=?2")
    List<Language> findByProjectId(String tenant, String projectId);


    @Query("SELECT l FROM Language l  " +
            " WHERE " +
            " l.tenant=?1 " +
            " AND " +
            " l.project.name=?2 ")
    List<Language> findByProjectName(String tenant, String projectName);


    /**
     * will use in Public api, so no tenant check here
     */
    @Query("SELECT l FROM Language l " +
            " WHERE " +
            " l.project.name=?1 " +
            " AND " +
            "l.code=?2")
    Optional<Language> findByProjectNameAndLanguageCode(String projectId, String code);


}
