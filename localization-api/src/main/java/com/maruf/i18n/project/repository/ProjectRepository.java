package com.maruf.i18n.project.repository;

import com.maruf.i18n.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p WHERE p.name=?1")
    Optional<Project> findByName(String name);
}
