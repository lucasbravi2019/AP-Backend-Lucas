package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.ProjectRequest;
import com.bravi.portfolio.dto.ProjectResponse;
import com.bravi.portfolio.dto.TechnologyResponse;
import com.bravi.portfolio.entity.Project;
import com.bravi.portfolio.entity.Technology;
import com.bravi.portfolio.repository.PersonaRepository;
import com.bravi.portfolio.repository.ProjectRepository;
import com.bravi.portfolio.repository.TechnologyRepository;
import com.bravi.portfolio.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProjectMapper {

    private final ProjectRepository projectRepository;
    private final PersonaRepository personaRepository;
    private final TechnologyRepository technologyRepository;
    private final FileService fileService;

    public Project toEntity(MultipartFile file, ProjectRequest projectRequest) {
        Project project = Project.builder().build();
        if (projectRequest.getId() != null) {
            project = projectRepository.findById(projectRequest.getId())
                    .orElseThrow();

            if (project.getImage() != null) {
                fileService.deleteFile(project.getImage());
                project.setImage(null);
            }
        }
        project.setProjectName(projectRequest.getProjectName());
        project.setProjectDescription(projectRequest.getProjectDescription());
        project.setImage(fileService.saveFile(file));
        project.setSite(projectRequest.getSite());
        if (projectRequest.getPersonaId() != null) {
            project.setPersona(personaRepository.findById(projectRequest.getPersonaId())
                    .orElseThrow());
        }
        if (!CollectionUtils.isEmpty(projectRequest.getTechnologyList())) {
            project.setTechnologyList(new HashSet<>(technologyRepository.findAllById(projectRequest.getTechnologyList())));
        }

        return project;
    }

    public ProjectResponse toDto(Project project) {
        ProjectResponse response = ProjectResponse.builder()
                .id(project.getId())
                .projectName(project.getProjectName())
                .projectDescription(project.getProjectDescription())
                .technologyList(mapTechnologies(project.getTechnologyList()))
                .site(project.getSite())
                .build();

        if (project.getImage() != null) {
            response.setImage(fileService.downloadFile(project.getImage()));
        }
        return response;
    }

    private Set<TechnologyResponse> mapTechnologies(Set<Technology> technologyList) {
        return technologyList.stream().map(technology -> {
                    TechnologyResponse response = TechnologyResponse.builder()
                            .id(technology.getId())
                            .level(technology.getLevel())
                            .name(technology.getName())
                            .build();

                    if (technology.getImage() != null) {
                        response.setImage(fileService.downloadFile(technology.getImage()));
                    }
                    return response;
                })
                .collect(Collectors.toSet());
    }

    public List<ProjectResponse> toDtoList(List<Project> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
