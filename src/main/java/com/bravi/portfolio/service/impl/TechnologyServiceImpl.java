package com.bravi.portfolio.service.impl;

import com.bravi.portfolio.dto.TechnologyRequest;
import com.bravi.portfolio.dto.TechnologyResponse;
import com.bravi.portfolio.mapper.TechnologyMapper;
import com.bravi.portfolio.repository.TechnologyRepository;
import com.bravi.portfolio.service.TechnologyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyMapper technologyMapper;
    private final TechnologyRepository technologyRepository;

    @Override
    public TechnologyResponse createTechnology(MultipartFile file, TechnologyRequest technologyRequest) {
        if (technologyRequest.getId() != null) {
            technologyRequest.setId(null);
        }
        return technologyMapper.toDto(technologyRepository.save(technologyMapper.toEntity(file, technologyRequest)));
    }

    @Override
    public TechnologyResponse editTechnology(MultipartFile file, TechnologyRequest technologyRequest) {
        return technologyMapper.toDto(technologyRepository.save(technologyMapper.toEntity(file, technologyRequest)));
    }

    @Override
    public void deleteTechnology(Long id) {
        technologyRepository.deleteById(id);
    }

}
