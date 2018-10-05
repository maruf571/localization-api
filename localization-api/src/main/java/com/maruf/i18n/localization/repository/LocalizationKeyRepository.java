package com.maruf.i18n.localization.repository;

import com.maruf.i18n.localization.entity.LocalizationKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LocalizationKeyRepository extends CrudRepository<LocalizationKey, String> {


    @Query("SELECT l FROM LocalizationKey l " +
            " LEFT JOIN FETCH l.localizationValues lv" +
            " WHERE l.tenant=?1 " +
            " AND " +
            " l.id=?2 ")
    LocalizationKey findById(String tenant, String localizationId);

    @Query("SELECT l FROM LocalizationKey l " +
            " LEFT JOIN FETCH l.localizationValues lv " +
            " WHERE l.project.id=?1  AND l.id=?2")
    LocalizationKey findByProjectIdAndLocalizatoinId(String projectId, String localizationId);

    @Query("SELECT l FROM LocalizationKey l " +
            " LEFT JOIN FETCH l.localizationValues " +
            " WHERE " +
            " l.tenant=?1 " +
            " AND " +
            " l.project.id=?2 " +
            " AND " +
            " l.langKey=?3")
    Optional<LocalizationKey> findByProjectIdAndKey(String tenant, String projectId, String key);


    @Query(value = "SELECT l.langKey from LOCALIZATION_KEY l" +
            " LEFT JOIN PROJECT p  ON p.id=l.project_id " +
            " WHERE p.name=?1 ",
            nativeQuery = true)
    List<String> getAllKeysByProject(String projectName);

}
