package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gymmanagement.gym_management.dtos.CourseContentRequest;
import com.gymmanagement.gym_management.dtos.CourseContentResponse;
import com.gymmanagement.gym_management.services.CourseContentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseContentController {
    private final CourseContentService service;

    @GetMapping("/{courseId}/contents")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER','MEMBER')")
    public ResponseEntity<List<CourseContentResponse>>
    getByCourse(@PathVariable Long courseId) {

        return ResponseEntity.ok(
                service.getByCourse(courseId));
    }

    @PostMapping(
            value = "/course-contents",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseContentResponse> create(
            @RequestPart("data") CourseContentRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request, file));
    }

    @DeleteMapping("/course-contents/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
