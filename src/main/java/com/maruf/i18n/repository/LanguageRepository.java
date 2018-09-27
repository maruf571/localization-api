package com.maruf.i18n.repository;

import com.maruf.i18n.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    List<Language> findByProjectId(Long projectId);

    List<Language> findByProjectName(String projectName);


}
