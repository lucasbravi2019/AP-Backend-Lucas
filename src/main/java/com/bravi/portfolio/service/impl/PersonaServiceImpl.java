package com.bravi.portfolio.service.impl;

import com.bravi.portfolio.dto.PersonaRequest;
import com.bravi.portfolio.dto.PersonaResponse;
import com.bravi.portfolio.mapper.PersonaMapper;
import com.bravi.portfolio.repository.PersonaRepository;
import com.bravi.portfolio.service.PersonaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaMapper personaMapper;
    private final PersonaRepository personaRepository;

    @Override
    public PersonaResponse getPersona() {
        return personaMapper.toDto(personaRepository.findAll().stream().findFirst().orElseThrow());
    }

    @Override
    public PersonaResponse editPersona(PersonaRequest personaRequest) {
        return personaMapper.toDto(personaRepository.save(personaMapper.toEntity(personaRequest)));
    }
}
