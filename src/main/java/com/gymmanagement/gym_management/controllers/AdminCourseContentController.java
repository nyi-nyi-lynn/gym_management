package com.gymmanagement.gym_management.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.gymmanagement.gym_management.dtos.CourseContentRequest;
import com.gymmanagement.gym_management.dtos.CourseContentResponse;
import com.gymmanagement.gym_management.services.CourseContentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/admin/course-contents")
@RequiredArgsConstructor
public class AdminCourseContentController {
     private final CourseContentService service;

    @PostMapping(
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CourseContentResponse> create(
            @RequestPart("data")
            CourseContentRequest request,

            @RequestPart(
                value = "file",
                required = false)
            MultipartFile file) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
