package com.bravi.portfolio.controller;

import com.bravi.portfolio.dto.ProjectRequest;
import com.bravi.portfolio.dto.ProjectResponse;
import com.bravi.portfolio.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @RequestParam("file") MultipartFile file,
            @RequestParam String projectName,
            @RequestParam String projectDescription,
            @RequestParam List<Long> technologyList,
            @RequestParam String site,
            @RequestParam Long personaId
    ) {
        ProjectRequest projectRequest = ProjectRequest.builder()
                .projectDescription(projectDescription)
                .projectName(projectName)
                .technologyList(technologyList)
                .personaId(personaId)
                .site(site)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(file, projectRequest));
    }

    @PutMapping
    public ResponseEntity<ProjectResponse> editProject(
            @RequestParam("file") MultipartFile file,
            @RequestParam String projectName,
            @RequestParam String projectDescription,
            @RequestParam List<Long> technologyList,
            @RequestParam Long personaId,
            @RequestParam String site,
            @RequestParam Long id
    ) {
        ProjectRequest projectRequest = ProjectRequest.builder()
                .id(id)
                .projectDescription(projectDescription)
                .projectName(projectName)
                .technologyList(technologyList)
                .personaId(personaId)
                .site(site)
                .build();
        return ResponseEntity.ok(projectService.editProject(file, projectRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }
}
