package com.gymmanagement.gym_management.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.gymmanagement.gym_management.enums.FileType;


@Component
public class FileValidator {
     public void validate(MultipartFile file, FileType type) {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String contentType = file.getContentType();

        switch (type) {
            case IMAGE -> {
                if (contentType == null || !contentType.startsWith("image/")) {
                    throw new RuntimeException("Only image files allowed");
                }
            }
            case VIDEO -> {
                if (contentType == null || !contentType.startsWith("video/")) {
                    throw new RuntimeException("Only video files allowed");
                }
            }
            case DOCUMENT -> {
                if (contentType == null ||
                        (!contentType.equals("application/pdf")
                         && !contentType.contains("word"))) {
                    throw new RuntimeException("Only PDF/Doc allowed");
                }
            }
        }
    }
}
