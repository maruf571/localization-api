package com.maruf.i18n.project.service;

import com.maruf.i18n.project.entity.Project;
import com.maruf.i18n.project.repository.ProjectRepository;
import com.maruf.i18n.tenant.TenantContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(Project project) {
        project.setTenant(TenantContext.getCurrentTenant());
        return projectRepository.save(project);
    }

    @Override
    public Project update(Project project) {
        projectRepository
                .findById(
                        TenantContext.getCurrentTenant(),
                        project.getId()
                )
                .orElseThrow(() -> new EntityNotFoundException("project not found"));

        return projectRepository.save(project);
    }

    @Override
    public Project findById(String projectId) {
        return projectRepository
                .findById(
                        TenantContext.getCurrentTenant(),
                        projectId
                )
                .orElseThrow(() -> new EntityNotFoundException("project not found"));
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return projectRepository.findAll(
                TenantContext.getCurrentTenant(),
                pageable
        );
    }

    @Override
    public void delete(String projectId) {
        projectRepository.deleteById(
                TenantContext.getCurrentTenant(),
                projectId
        );
    }
}
