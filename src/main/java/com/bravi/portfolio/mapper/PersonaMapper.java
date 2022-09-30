package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.*;
import com.bravi.portfolio.entity.*;
import com.bravi.portfolio.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PersonaMapper {

    private final PersonaRepository personaRepository;
    private final AboutMapper aboutMapper;
    private final ContactMapper contactMapper;
    private final JobMapper jobMapper;
    private final ProjectMapper projectMapper;
    private final TechnologyMapper technologyMapper;
    private final EducationMapper educationMapper;

    public Persona toEntity(PersonaRequest personaRequest) {
        Persona persona = Persona.builder().build();
        if (personaRequest.getId() != null) {
            persona = personaRepository.findById(personaRequest.getId())
                    .orElseThrow();
        }
        persona.setFirstName(personaRequest.getFirstName());
        persona.setLastName(personaRequest.getLastName());

        return persona;
    }

    public PersonaResponse toDto(Persona persona) {
        PersonaResponse personaResponse = PersonaResponse.builder()
                .id(persona.getId())
                .firstName(persona.getFirstName())
                .lastName(persona.getLastName())
                .build();

        if (persona.getAbout() != null) {
            personaResponse.setAbout(aboutMapper.toDto(persona.getAbout()));
        }

        if (!CollectionUtils.isEmpty(persona.getContactList())) {
            personaResponse.setContactList(contactMapper.toDtoList(persona.getContactList()));
        }

        if (!CollectionUtils.isEmpty(persona.getEducationList())) {
            personaResponse.setEducationList(educationMapper.toDtoList(persona.getEducationList()));
        }

        if (!CollectionUtils.isEmpty(persona.getJobList())) {
            personaResponse.setJobList(jobMapper.toDtoList(persona.getJobList()));
        }

        if (!CollectionUtils.isEmpty(persona.getProjectList())) {
            personaResponse.setProjectList(projectMapper.toDtoList(persona.getProjectList()));
        }

        if (!CollectionUtils.isEmpty(persona.getTechnologyList())) {
            personaResponse.setTechnologyList(technologyMapper.toDtoList(persona.getTechnologyList()));
        }

        return personaResponse;
    }



}
