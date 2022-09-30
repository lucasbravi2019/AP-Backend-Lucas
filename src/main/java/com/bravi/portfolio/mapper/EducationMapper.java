package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.EducationRequest;
import com.bravi.portfolio.dto.EducationResponse;
import com.bravi.portfolio.entity.Education;
import com.bravi.portfolio.repository.EducationRepository;
import com.bravi.portfolio.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class EducationMapper {

    private final EducationRepository educationRepository;
    private final PersonaRepository personaRepository;

    public Education toEntity(EducationRequest educationRequest) {
        Education education = Education.builder().build();
        if (educationRequest.getId() != null) {
            education = educationRepository.findById(educationRequest.getId())
                    .orElseThrow();
        }
        education.setInstitute(educationRequest.getInstitute());
        education.setTitle(educationRequest.getTitle());
        education.setStartDate(educationRequest.getStartDate());
        education.setEndDate(getEndDate(educationRequest.getEndDate(), educationRequest.getIsPresent()));
        if (educationRequest.getPersonaId() != null) {
            education.setPersona(personaRepository.findById(educationRequest.getPersonaId())
                    .orElseThrow());
        }

        return education;
    }

    private LocalDate getEndDate(LocalDate endDate, Boolean isPresent) {
        if (Boolean.TRUE.equals(isPresent)) {
            return LocalDate.of(9_999, 9, 9);
        }
        return endDate;
    }

    public EducationResponse toDto(Education education) {
        return EducationResponse.builder()
                .id(education.getId())
                .institute(education.getInstitute())
                .startDate(formatDate(education.getStartDate()))
                .endDate(formatDate(education.getEndDate()))
                .title(education.getTitle())
                .build();
    }

    private String formatDate(LocalDate date) {
        log.info("Date: {}", date);
        if (LocalDate.of(9999, 9, 9).equals(date)) {
            return "Present";
        }
        String pattern = "dd/MM/yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(date);
    }

    public List<EducationResponse> toDtoList(List<Education> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
