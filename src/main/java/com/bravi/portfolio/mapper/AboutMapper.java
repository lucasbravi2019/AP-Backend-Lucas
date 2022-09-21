package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.AboutRequest;
import com.bravi.portfolio.dto.AboutResponse;
import com.bravi.portfolio.entity.About;
import com.bravi.portfolio.repository.AboutRepository;
import com.bravi.portfolio.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AboutMapper {

    private final AboutRepository aboutRepository;
    private final PersonaRepository personaRepository;

    public About toEntity(AboutRequest aboutRequest) {
        About about = About.builder().build();
        if (aboutRequest.getId() != null) {
            about = aboutRepository.findById(aboutRequest.getId())
                    .orElseThrow();
        }

        about.setAboutMsg(aboutRequest.getAboutMsg());
        if (aboutRequest.getPersonaId() != null) {
            about.setPersona(personaRepository.findById(aboutRequest.getPersonaId())
                    .orElseThrow());
        }

        return about;
    }

    public AboutResponse toDto(About about) {
        return AboutResponse.builder()
                .id(about.getId())
                .aboutMsg(about.getAboutMsg())
                .build();
    }

}
