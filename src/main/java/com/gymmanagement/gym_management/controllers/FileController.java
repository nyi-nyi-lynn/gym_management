package com.gymmanagement.gym_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gymmanagement.gym_management.dtos.FileUploadResponse;
import com.gymmanagement.gym_management.enums.FileType;
import com.gymmanagement.gym_management.services.FileStorageService;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private FileStorageService service;

     @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER','MEMBER')")
    public FileUploadResponse upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") FileType type) {

        String url = service.store(file, type);
        return new FileUploadResponse(url);
    }
}
