package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.AboutRequest;
import com.bravi.portfolio.dto.AboutResponse;
import com.bravi.portfolio.entity.About;
import com.bravi.portfolio.repository.AboutRepository;
import com.bravi.portfolio.repository.PersonaRepository;
import com.bravi.portfolio.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Component
public class AboutMapper {

    private final AboutRepository aboutRepository;
    private final PersonaRepository personaRepository;
    private final FileService fileService;

    public About toEntity(MultipartFile file, AboutRequest aboutRequest) {
        About about = About.builder().build();
        if (aboutRequest.getId() != null) {
            about = aboutRepository.findById(aboutRequest.getId())
                    .orElseThrow();
            if (about.getImage() != null) {
                fileService.deleteFile(about.getImage());
                about.setImage(null);
            }
        }

        about.setAboutMsg(aboutRequest.getAboutMsg());
        about.setImage(fileService.saveFile(file));
        if (aboutRequest.getPersonaId() != null) {
            about.setPersona(personaRepository.findById(aboutRequest.getPersonaId())
                    .orElseThrow());
        }

        return about;
    }

    public AboutResponse toDto(About about) {
        AboutResponse response = AboutResponse.builder()
                .id(about.getId())
                .aboutMsg(about.getAboutMsg())
                .build();

        if (about.getImage() != null) {
            response.setImage(fileService.downloadFile(about.getImage()));
        }
        return response;
    }

}
