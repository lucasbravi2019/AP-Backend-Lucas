package com.bravi.portfolio.service.impl;

import com.bravi.portfolio.dto.AboutRequest;
import com.bravi.portfolio.dto.AboutResponse;
import com.bravi.portfolio.mapper.AboutMapper;
import com.bravi.portfolio.repository.AboutRepository;
import com.bravi.portfolio.service.AboutService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class AboutServiceImpl implements AboutService {

    private AboutMapper aboutMapper;
    private AboutRepository aboutRepository;

    @Override
    public AboutResponse editAbout(MultipartFile file, AboutRequest aboutRequest) {
        return aboutMapper.toDto(aboutRepository.save(aboutMapper.toEntity(aboutRequest)));
    }

}
