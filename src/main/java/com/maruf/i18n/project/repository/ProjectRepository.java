package com.maruf.i18n.project.repository;

import com.maruf.i18n.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("SELECT p FROM Project p WHERE p.tenant=?1 AND p.id=?2")
    Optional<Project> findById(String tenant, String id);

    @Query("SELECT p FROM Project p WHERE p.name=?1")
    Optional<Project> findByName(String name);

    @Query("SELECT p FROM Project p WHERE p.tenant=?1")
    Page<Project> findAll(String tenant, Pageable pageable);

    @Modifying
    @Query("DELETE FROM Project p WHERE p.tenant=?1 AND p.id=?2")
    void deleteById(String tenant, String id);
}
