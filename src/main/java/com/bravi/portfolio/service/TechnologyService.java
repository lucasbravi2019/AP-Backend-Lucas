package com.bravi.portfolio.service;

import com.bravi.portfolio.dto.TechnologyRequest;
import com.bravi.portfolio.dto.TechnologyResponse;
import com.bravi.portfolio.entity.Technology;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TechnologyService {

    TechnologyResponse createTechnology(MultipartFile file, TechnologyRequest technologyRequest);
    TechnologyResponse editTechnology(MultipartFile file, TechnologyRequest technologyRequest);
    void deleteTechnology(Long id);

}
