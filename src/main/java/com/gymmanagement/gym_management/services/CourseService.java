package com.gymmanagement.gym_management.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.gymmanagement.gym_management.dtos.CourseRequest;
import com.gymmanagement.gym_management.dtos.CourseResponse;

public interface CourseService {
    CourseResponse create(CourseRequest request, MultipartFile thumbnail);

    CourseResponse update(Long id, CourseRequest request, MultipartFile thumbnail);

    void deactivate(Long id);

    Page<CourseResponse> getAllActive(Pageable pageable);

    Page<CourseResponse> getByCategory(Long categoryId, Pageable pageable);
}
