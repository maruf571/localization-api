package com.maruf.i18n.client;

import com.maruf.i18n.entity.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "localization-api")
public interface ProjectProxy {

    @PostMapping("/api/protected/projects")
    Project create(@RequestBody Project project);

    @PutMapping("/api/protected/projects")
    Project update(@RequestBody Project project);

    @GetMapping("/api/protected/projects/{projectId}")
    Project findById(@PathVariable String projectId);

    @GetMapping("/api/protected/projects")
    Page<Project> findAll(Pageable pageable);

    @DeleteMapping("/api/protected/projects/{projectId}")
    public void delete(@PathVariable String projectId);

}
