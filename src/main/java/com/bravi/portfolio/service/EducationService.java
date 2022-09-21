package com.bravi.portfolio.service;

import com.bravi.portfolio.dto.EducationRequest;
import com.bravi.portfolio.dto.EducationResponse;

public interface EducationService {

    EducationResponse createEducation(EducationRequest educationRequest);
    EducationResponse editEducation(EducationRequest educationRequest);
    void deleteEducation(Long id);

}
