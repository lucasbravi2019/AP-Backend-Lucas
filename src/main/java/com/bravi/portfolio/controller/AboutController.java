package com.bravi.portfolio.controller;

import com.bravi.portfolio.dto.AboutRequest;
import com.bravi.portfolio.dto.AboutResponse;
import com.bravi.portfolio.service.AboutService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/about")
public class AboutController {

    private final AboutService aboutService;

    @PutMapping
    public ResponseEntity<AboutResponse> editAbout(
            @RequestParam("file") MultipartFile file,
            @RequestParam String aboutMsg,
            @RequestParam Long personaId,
            @RequestParam Long id
    ) {
        AboutRequest aboutRequest = AboutRequest.builder()
                .id(id)
                .aboutMsg(aboutMsg)
                .personaId(personaId)
                .build();

        return ResponseEntity.ok(aboutService.editAbout(file, aboutRequest));
    }
}
