package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.TechnologyRequest;
import com.bravi.portfolio.dto.TechnologyResponse;
import com.bravi.portfolio.entity.Technology;
import com.bravi.portfolio.repository.PersonaRepository;
import com.bravi.portfolio.repository.TechnologyRepository;
import com.bravi.portfolio.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class TechnologyMapper {

    private final TechnologyRepository technologyRepository;
    private final PersonaRepository personaRepository;
    private final FileService fileService;

    public Technology toEntity(MultipartFile file, TechnologyRequest technologyRequest) {
        Technology technology = Technology.builder().build();
        if (technologyRequest.getId() != null) {
            technology = technologyRepository.findById(technologyRequest.getId())
                    .orElseThrow();

            if (technology.getImage() != null) {
                fileService.deleteFile(technology.getImage());
                technology.setImage(null);
            }
        }
        technology.setLevel(technologyRequest.getLevel());
        technology.setName(technologyRequest.getName());
        technology.setImage(fileService.saveFile(file));
        if (technologyRequest.getPersonaId() != null) {
            technology.setPersona(personaRepository.findById(technologyRequest.getPersonaId())
                    .orElseThrow());
        }

        return technology;
    }

    public TechnologyResponse toDto(Technology technology) {
        TechnologyResponse response = TechnologyResponse.builder()
                .id(technology.getId())
                .name(technology.getName())
                .level(technology.getLevel())
                .build();

        if (technology.getImage() != null) {
            response.setImage(fileService.downloadFile(technology.getImage()));
        }
        return response;
    }

    public List<TechnologyResponse> toDtoList(List<Technology> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
