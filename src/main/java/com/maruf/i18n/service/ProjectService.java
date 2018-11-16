package com.maruf.i18n.service;

import com.maruf.i18n.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    Project create(Project project);

    Project update(Project project);

    Project findById(String projectId);

    Page<Project> findAll(Pageable pageable);

    void delete(String projectId);
}
