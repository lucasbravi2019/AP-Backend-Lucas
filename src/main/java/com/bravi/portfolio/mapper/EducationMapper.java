package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.EducationRequest;
import com.bravi.portfolio.dto.EducationResponse;
import com.bravi.portfolio.entity.Education;
import com.bravi.portfolio.repository.EducationRepository;
import com.bravi.portfolio.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EducationMapper {

    private final EducationRepository educationRepository;
    private final PersonaRepository personaRepository;

    public Education toEntity(EducationRequest educationRequest) {
        Education education = Education.builder().build();
        if (educationRequest.getId() != null) {
            education = educationRepository.findById(education.getId())
                    .orElseThrow();
        }
        education.setInstitute(educationRequest.getInstitute());
        education.setTitle(educationRequest.getTitle());
        education.setStartDate(educationRequest.getStartDate());
        education.setEndDate(educationRequest.getEndDate());
        if (educationRequest.getPersonaId() != null) {
            education.setPersona(personaRepository.findById(educationRequest.getPersonaId())
                    .orElseThrow());
        }

        return education;
    }

    public EducationResponse toDto(Education education) {
        return EducationResponse.builder()
                .id(education.getId())
                .institute(education.getInstitute())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .title(education.getTitle())
                .build();
    }
}
