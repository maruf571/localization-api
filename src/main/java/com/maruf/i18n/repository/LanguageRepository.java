package com.maruf.i18n.repository;

import com.maruf.i18n.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, String> {

    @Query("SELECT l from Language l " +
            " LEFT JOIN FETCH l.project lp" +
            " WHERE " +
            " l.id=:languageId")
    Optional<Language> findByLanguageId(@Param("languageId") String languageId);


    @Query("SELECT l from Language l " +
            " LEFT JOIN FETCH l.project lp " +
            " WHERE " +
            " lp.id=:projectId")
    List<Language> findByProjectId(@Param("projectId") String projectId);


    @Query("SELECT l FROM Language l  " +
            " WHERE " +
            " l.project.name=:projectName ")
    List<Language> findByProjectName(@Param("projectName") String projectName);


    /**
     * will use in Public api, so no tenant check here
     */
    @Query("SELECT l FROM Language l " +
            " WHERE " +
            " l.project.name=:projectName " +
            " AND " +
            " l.code=:code ")
    Optional<Language> findByProjectNameAndLanguageCode(@Param("projectName") String projectName, @Param("code") String code);


}
