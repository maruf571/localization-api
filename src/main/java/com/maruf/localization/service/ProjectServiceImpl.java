package com.maruf.localization.service;

import com.maruf.localization.entity.Project;
import com.maruf.localization.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project update(Project project) {
        projectRepository.findByProjectId(project.getId())
                .orElseThrow(() -> new EntityNotFoundException("project not found"));
        return projectRepository.save(project);
    }

    @Override
    public Project findById(String projectId) {
        return projectRepository
                .findByProjectId(projectId)
                .orElseThrow(() -> new EntityNotFoundException("project not found"));
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return projectRepository.findAllProject(pageable);
    }

    @Override
    public void delete(String projectId) {
        projectRepository.deleteByProjectId(projectId);
    }
}
