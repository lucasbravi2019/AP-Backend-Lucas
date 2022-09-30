package com.bravi.portfolio.service.impl;

import com.bravi.portfolio.dto.EducationRequest;
import com.bravi.portfolio.dto.EducationResponse;
import com.bravi.portfolio.mapper.EducationMapper;
import com.bravi.portfolio.repository.EducationRepository;
import com.bravi.portfolio.service.EducationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class EducationServiceImpl implements EducationService {

    private final EducationMapper educationMapper;
    private final EducationRepository educationRepository;

    @Override
    public EducationResponse createEducation(EducationRequest educationRequest) {
        if (educationRequest.getId() != null) {
            educationRequest.setId(null);
        }
        return educationMapper.toDto(educationRepository.save(educationMapper.toEntity(educationRequest)));
    }

    @Override
    public EducationResponse editEducation(EducationRequest educationRequest) {
        if (!educationRepository.existsById(educationRequest.getId())) {
            throw new EntityNotFoundException("Education not found");
        }
        return educationMapper.toDto(educationRepository.save(educationMapper.toEntity(educationRequest)));
    }

    @Override
    public void deleteEducation(Long id) {
        if (!educationRepository.existsById(id)) {
            throw new EntityNotFoundException("Education not found");
        }
        educationRepository.deleteById(id);
    }
}
