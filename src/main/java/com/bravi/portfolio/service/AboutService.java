package com.bravi.portfolio.service;

import com.bravi.portfolio.dto.AboutRequest;
import com.bravi.portfolio.dto.AboutResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AboutService {

    AboutResponse editAbout(MultipartFile file, AboutRequest aboutRequest);
}
