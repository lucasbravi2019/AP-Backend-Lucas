package com.bravi.portfolio.service;

import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

public interface FileService {

    String saveFile(MultipartFile file);
    byte[] downloadFile(String fileName);
    void deleteFile(String fileName);

}
