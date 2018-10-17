package com.maruf.i18n.project.service;

import com.maruf.i18n.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    Project create(Project project);

    Project update(Project project);

    Project findById(String projectId);

    Page<Project> findAll(Pageable pageable);

    void delete(String projectId);
}
