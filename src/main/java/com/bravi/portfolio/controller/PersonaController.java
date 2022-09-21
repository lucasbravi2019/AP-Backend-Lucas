package com.bravi.portfolio.controller;

import com.bravi.portfolio.dto.PersonaRequest;
import com.bravi.portfolio.dto.PersonaResponse;
import com.bravi.portfolio.service.PersonaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping
    public ResponseEntity<PersonaResponse> getPersona() {
        return ResponseEntity.ok(personaService.getPersona());
    }

    @PutMapping
    public ResponseEntity<PersonaResponse> editPersona(@Valid @RequestBody PersonaRequest personaRequest) {
        return ResponseEntity.ok(personaService.editPersona(personaRequest));
    }

}
