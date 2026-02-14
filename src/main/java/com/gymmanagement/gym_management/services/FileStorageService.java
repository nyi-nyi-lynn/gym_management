package com.gymmanagement.gym_management.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gymmanagement.gym_management.enums.FileType;
import com.gymmanagement.gym_management.validation.FileValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileStorageService {
    private final  FileValidator validator;

    private static final String BASE_DIR = "uploads";

    public String store(MultipartFile file, FileType type) {

        validator.validate(file, type);

        try {
            String folderName = type.name().toLowerCase();

            Path uploadPath = Paths.get(BASE_DIR, folderName);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + folderName + "/" + fileName;

        } catch (IOException e) {
            throw new RuntimeException("File upload failed");
        }
    }
}
