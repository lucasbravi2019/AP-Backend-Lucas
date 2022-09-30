package com.bravi.portfolio.service.impl;

import com.bravi.portfolio.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

import static java.nio.file.Path.*;
import static java.nio.file.Paths.get;

@Service
public class FileServiceImpl implements FileService, CommandLineRunner {

    @Value("${application.file.storage:src/main/resources/files}")
    private String filesDirectory;

    @Override
    public String saveFile(MultipartFile multipartFile) {
        try {
            Path path = get(filesDirectory);
            Path filePath = path.resolve(Instant.now().toEpochMilli() + multipartFile.getOriginalFilename());
            File file = new File(filePath.toString());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.close();
            return path.toUri().relativize(file.toURI()).toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public byte[] downloadFile(String fileName) {
        try {
            Path path = get(filesDirectory);
            InputStream inputStream = new FileInputStream(path.resolve(fileName).toString());
            byte[] file = inputStream.readAllBytes();
            inputStream.close();
            return file;
        } catch (IOException ex) {
            deleteFile(fileName);
        }
        return null;
    }

    @Override
    public void deleteFile(String fileName) {
        Path path = get(filesDirectory);
        File file = new File(path.resolve(fileName).toString());
        try {
            Files.delete(file.toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void run(String... args) throws Exception {
        if (!Files.isDirectory(of(filesDirectory))) {
            try {
                Files.createDirectory(of(filesDirectory));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
