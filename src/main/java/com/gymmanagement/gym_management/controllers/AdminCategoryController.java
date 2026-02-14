package com.gymmanagement.gym_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.CategoryCreateRequest;
import com.gymmanagement.gym_management.dtos.CategoryResponse;
import com.gymmanagement.gym_management.dtos.CategoryUpdateRequest;
import com.gymmanagement.gym_management.enums.CategoryStatus;
import com.gymmanagement.gym_management.services.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping
    public CategoryResponse create(@Valid @RequestBody CategoryCreateRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public CategoryResponse update( @PathVariable Long id,@Valid @RequestBody CategoryUpdateRequest request) {
        return service.update(id, request);
    }
    
     @GetMapping
    public Page<CategoryResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) CategoryStatus status,
            Pageable pageable) {

        return service.search(keyword, status, pageable);
    }
}
