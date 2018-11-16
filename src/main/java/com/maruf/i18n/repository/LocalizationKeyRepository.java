package com.maruf.i18n.repository;

import com.maruf.i18n.entity.LocalizationKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LocalizationKeyRepository extends CrudRepository<LocalizationKey, String> {


    @Query("SELECT lk FROM LocalizationKey lk " +
            " LEFT JOIN FETCH lk.localizationValues lv" +
            " WHERE " +
            " lk.id=:localizationId ")
    Optional<LocalizationKey> findByLocalizationId(@Param("localizationId") String localizationId);


    @Query("SELECT lk FROM LocalizationKey lk " +
            " LEFT JOIN FETCH lk.localizationValues lv " +
            " WHERE " +
            " lk.project.id=:projectId  " +
            " AND " +
            " lk.id=:localizationId ")
    LocalizationKey findByProjectIdAndLocalizationId(@Param("projectId") String projectId, @Param("localizationId") String localizationId);

    @Query("SELECT lk FROM LocalizationKey lk " +
            " LEFT JOIN FETCH lk.localizationValues " +
            " WHERE " +
            " lk.project.id=:projectId " +
            " AND " +
            " lk.langKey=:key ")
    Optional<LocalizationKey> findByProjectIdAndKey(@Param("projectId")  String projectId, @Param("key")  String key);


    @Query(value = "SELECT l.langKey from LOCALIZATION_KEY l" +
            " LEFT JOIN PROJECT p  ON p.id=l.project_id " +
            " WHERE p.name=:projectName ",
            nativeQuery = true)
    List<String> getAllKeysByProject(@Param("projectName") String projectName);

}
