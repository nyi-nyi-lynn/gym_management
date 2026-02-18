package com.gymmanagement.gym_management.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gymmanagement.gym_management.dtos.CategoryCreateRequest;
import com.gymmanagement.gym_management.dtos.CategoryResponse;
import com.gymmanagement.gym_management.dtos.CategoryUpdateRequest;
import com.gymmanagement.gym_management.enums.CategoryStatus;

public interface CategoryService {
    CategoryResponse create(CategoryCreateRequest request);

    CategoryResponse update(Long id , CategoryUpdateRequest request);

    void deactivate(Long id);

    Page<CategoryResponse> search(String keyword, CategoryStatus status,Pageable pageable);

    List<CategoryResponse> getActive();


}
