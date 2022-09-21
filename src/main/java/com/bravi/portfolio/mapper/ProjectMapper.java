package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.ProjectRequest;
import com.bravi.portfolio.dto.ProjectResponse;
import com.bravi.portfolio.dto.TechnologyResponse;
import com.bravi.portfolio.entity.Project;
import com.bravi.portfolio.entity.Technology;
import com.bravi.portfolio.repository.PersonaRepository;
import com.bravi.portfolio.repository.ProjectRepository;
import com.bravi.portfolio.repository.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProjectMapper {

    private final ProjectRepository projectRepository;
    private final PersonaRepository personaRepository;
    private final TechnologyRepository technologyRepository;

    public Project toEntity(ProjectRequest projectRequest) {
        Project project = Project.builder().build();
        if (projectRequest.getId() != null) {
            project = projectRepository.findById(project.getId())
                    .orElseThrow();
        }
        project.setProjectName(projectRequest.getProjectName());
        project.setProjectDescription(projectRequest.getProjectDescription());
        if (projectRequest.getPersonaId() != null) {
            project.setPersona(personaRepository.findById(projectRequest.getPersonaId())
                    .orElseThrow());
        }
        if (!CollectionUtils.isEmpty(projectRequest.getTechnologyList())) {
            project.setTechnologyList(technologyRepository.findAllById(projectRequest.getTechnologyList()));
        }

        return project;
    }

    public ProjectResponse toDto(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .projectName(project.getProjectName())
                .projectDescription(project.getProjectDescription())
                .technologyList(mapTechnologies(project.getTechnologyList()))
                .build();
    }

    private List<TechnologyResponse> mapTechnologies(List<Technology> technologyList) {
        return technologyList.stream().map(technology -> TechnologyResponse.builder()
                        .id(technology.getId())
                        .level(technology.getLevel())
                        .name(technology.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
