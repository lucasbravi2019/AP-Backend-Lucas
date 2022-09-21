package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.TechnologyRequest;
import com.bravi.portfolio.dto.TechnologyResponse;
import com.bravi.portfolio.entity.Technology;
import com.bravi.portfolio.repository.PersonaRepository;
import com.bravi.portfolio.repository.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TechnologyMapper {

    private final TechnologyRepository technologyRepository;
    private final PersonaRepository personaRepository;

    public Technology toEntity(TechnologyRequest technologyRequest) {
        Technology technology = Technology.builder().build();
        if (technologyRequest.getId() != null) {
            technology = technologyRepository.findById(technologyRequest.getId())
                    .orElseThrow();
        }
        technology.setLevel(technologyRequest.getLevel());
        technology.setName(technologyRequest.getName());
        if (technologyRequest.getPersonaId() != null) {
            technology.setPersona(personaRepository.findById(technologyRequest.getPersonaId())
                    .orElseThrow());
        }

        return technology;
    }

    public TechnologyResponse toDto(Technology technology) {
        return TechnologyResponse.builder()
                .id(technology.getId())
                .name(technology.getName())
                .level(technology.getLevel())
                .build();
    }
}
