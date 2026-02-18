package com.gymmanagement.gym_management.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gymmanagement.gym_management.dtos.CourseContentRequest;
import com.gymmanagement.gym_management.dtos.CourseContentResponse;

public interface CourseContentService {
    CourseContentResponse create(
            CourseContentRequest request,
            MultipartFile file);

    void delete(Long id);

    List<CourseContentResponse> getByCourse(Long courseId);
}
