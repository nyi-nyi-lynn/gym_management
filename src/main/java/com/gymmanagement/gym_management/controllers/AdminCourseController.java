package com.gymmanagement.gym_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gymmanagement.gym_management.dtos.CourseRequest;
import com.gymmanagement.gym_management.dtos.CourseResponse;
import com.gymmanagement.gym_management.services.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/courses")
public class AdminCourseController {
    @Autowired
    private CourseService courseService;

      // CREATE COURSE
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CourseResponse> create(
            @RequestPart("data") @Valid CourseRequest request,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail) {

        CourseResponse response = courseService.create(request, thumbnail);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // UPDATE COURSE
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CourseResponse> update(
            @PathVariable Long id,
            @RequestPart("data") @Valid CourseRequest request,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail) {

        CourseResponse response = courseService.update(id, request, thumbnail);
        return ResponseEntity.ok(response);
    }

    // DEACTIVATE COURSE (Soft Delete)
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {

        courseService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
