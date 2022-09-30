package com.bravi.portfolio.controller;

import com.bravi.portfolio.dto.EducationRequest;
import com.bravi.portfolio.dto.EducationResponse;
import com.bravi.portfolio.service.EducationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDate;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/education")
public class EducationController {

    private final EducationService educationService;

    @PostMapping
    public ResponseEntity<EducationResponse> createEducation(@Valid @RequestBody EducationRequest educationRequest) {
        return ResponseEntity.ok(educationService.createEducation(educationRequest));
    }

    @PutMapping
    public ResponseEntity<EducationResponse> editEducation(@Valid @RequestBody EducationRequest educationRequest) {
        return ResponseEntity.ok(educationService.editEducation(educationRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
        return ResponseEntity.ok().build();
    }
}
