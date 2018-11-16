package com.maruf.i18n.repository;

import com.maruf.i18n.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("SELECT p FROM Project p WHERE p.id=:projectId")
    Optional<Project> findByProjectId(@Param("projectId") String projectId);

    @Query("SELECT p FROM Project p WHERE p.name=:projectName")
    Optional<Project> findByProjectName(@Param("projectName") String projectName);

    @Query("SELECT p FROM Project p")
    Page<Project> findAllProject(Pageable pageable);

    @Modifying
    @Query("DELETE FROM Project p WHERE p.id=:projectId")
    void deleteByProjectId(@Param("projectId") String projectId);
}
