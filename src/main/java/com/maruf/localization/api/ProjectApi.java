package com.maruf.localization.api;

import com.maruf.localization.entity.Project;
import com.maruf.localization.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/projects")
public class ProjectApi {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.create(project));
    }

    @PutMapping
    public ResponseEntity<Project> update(@RequestBody Project project){
        return ResponseEntity.ok()
                .body(projectService.update(project));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> findById(@PathVariable String projectId){
        return ResponseEntity.ok()
                .body(projectService.findById(projectId));
    }

    @GetMapping
    public ResponseEntity<Page<Project>> findAll(Pageable pageable){
        return ResponseEntity.ok()
                .body(projectService.findAll(pageable));
    }

    @DeleteMapping("/{projectId}")
    public void delete(@PathVariable String projectId){
        projectService.delete(projectId);
    }

}
