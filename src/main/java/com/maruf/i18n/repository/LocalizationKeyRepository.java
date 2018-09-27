package com.maruf.i18n.repository;

import com.maruf.i18n.entity.LocalizationKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocalizationKeyRepository extends CrudRepository<LocalizationKey, Long> {


    @Query("SELECT l FROM LocalizationKey l " +
            " LEFT JOIN FETCH l.localizationValues " +
            " WHERE l.project.name=?1 AND l.langKey=?2")
    LocalizationKey findByProjectNameAndKey(String projectName, String key);


    @Query(value = "SELECT l.langKey from LOCALIZATION_KEY l" +
            " LEFT JOIN PROJECT p  ON p.id=l.project_id " +
            " WHERE p.name=?1 ",
            nativeQuery = true)
    List<String> getAllKeysByProject(String projectName);

}
