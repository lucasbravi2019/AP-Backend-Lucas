package com.bravi.portfolio.service.impl;

import com.bravi.portfolio.dto.ProjectRequest;
import com.bravi.portfolio.dto.ProjectResponse;
import com.bravi.portfolio.mapper.ProjectMapper;
import com.bravi.portfolio.repository.ProjectRepository;
import com.bravi.portfolio.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponse createProject(MultipartFile file, ProjectRequest projectRequest) {
        return projectMapper.toDto(projectRepository.save(projectMapper.toEntity(projectRequest)));
    }

    @Override
    public ProjectResponse editProject(MultipartFile file, ProjectRequest projectRequest) {
        return projectMapper.toDto(projectRepository.save(projectMapper.toEntity(projectRequest)));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

}
