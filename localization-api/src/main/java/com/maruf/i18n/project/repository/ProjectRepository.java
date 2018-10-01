package com.maruf.i18n.project.repository;

import com.maruf.i18n.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p WHERE p.tenant=?1 AND p.id=?2")
    Optional<Project> findById(Long tenant, Long id);

    @Query("SELECT p FROM Project p WHERE p.tenant=?1 AND p.name=?2")
    Optional<Project> findByName(Long tenant, String name);

    @Query("SELECT p FROM Project p WHERE p.tenant=?1")
    Page<Project> findAll(Long tenant, Pageable pageable);

    @Modifying
    @Query("DELETE FROM Project p WHERE p.tenant=?1 AND p.id=?2")
    void deleteById(Long tenant, Long id);
}
