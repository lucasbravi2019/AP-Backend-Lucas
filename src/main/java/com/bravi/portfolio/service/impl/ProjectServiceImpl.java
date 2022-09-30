package com.bravi.portfolio.service.impl;

import com.bravi.portfolio.dto.ProjectRequest;
import com.bravi.portfolio.dto.ProjectResponse;
import com.bravi.portfolio.entity.Project;
import com.bravi.portfolio.mapper.ProjectMapper;
import com.bravi.portfolio.repository.ProjectRepository;
import com.bravi.portfolio.service.FileService;
import com.bravi.portfolio.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final FileService fileService;

    @Override
    public ProjectResponse createProject(MultipartFile file, ProjectRequest projectRequest) {
        return projectMapper.toDto(projectRepository.save(projectMapper.toEntity(file, projectRequest)));
    }

    @Override
    public ProjectResponse editProject(MultipartFile file, ProjectRequest projectRequest) {
        return projectMapper.toDto(projectRepository.save(projectMapper.toEntity(file, projectRequest)));
    }

    @Override
    public void deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            Project project = projectRepository.findById(id).orElseThrow();
            if (project.getImage() != null) {
                fileService.deleteFile(project.getImage());
            }
        }
        projectRepository.deleteById(id);
    }

}
