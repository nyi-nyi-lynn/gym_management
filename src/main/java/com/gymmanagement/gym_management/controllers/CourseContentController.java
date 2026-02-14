package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gymmanagement.gym_management.dtos.CourseContentResponse;
import com.gymmanagement.gym_management.services.CourseContentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/course-contents")
@RequiredArgsConstructor
public class CourseContentController {
    private final CourseContentService service;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseContentResponse>>
    getByCourse(@PathVariable Long courseId) {

        return ResponseEntity.ok(
                service.getByCourse(courseId));
    }
}
