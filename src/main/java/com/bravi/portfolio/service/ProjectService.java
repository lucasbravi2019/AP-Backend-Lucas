package com.bravi.portfolio.service;

import com.bravi.portfolio.dto.ProjectRequest;
import com.bravi.portfolio.dto.ProjectResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectService {

    ProjectResponse createProject(MultipartFile file, ProjectRequest projectRequest);
    ProjectResponse editProject(MultipartFile file, ProjectRequest projectRequest);
    void deleteProject(Long id);

}
