package com.bravi.portfolio.controller;

import com.bravi.portfolio.dto.TechnologyRequest;
import com.bravi.portfolio.dto.TechnologyResponse;
import com.bravi.portfolio.service.TechnologyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/technology")
public class TechnologyController {

    private final TechnologyService technologyService;

    @PostMapping
    public ResponseEntity<TechnologyResponse> createTechnology(
            @RequestParam("file") MultipartFile file,
            @RequestParam String name,
            @RequestParam Integer level,
            @RequestParam Long personaId
    ) {
        TechnologyRequest technologyRequest = TechnologyRequest.builder()
                .personaId(personaId)
                .name(name)
                .level(level)
                .build();

        return ResponseEntity.ok(technologyService.createTechnology(file, technologyRequest));
    }

    @PutMapping
    public ResponseEntity<TechnologyResponse> editTechnology(
            @RequestParam("file") MultipartFile file,
            @RequestParam String name,
            @RequestParam Integer level,
            @RequestParam Long personaId,
            @RequestParam Long id
    ) {
        TechnologyRequest technologyRequest = TechnologyRequest.builder()
                .id(id)
                .personaId(personaId)
                .name(name)
                .level(level)
                .build();

        return ResponseEntity.ok(technologyService.editTechnology(file, technologyRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id) {
        technologyService.deleteTechnology(id);
        return ResponseEntity.ok().build();
    }
}
