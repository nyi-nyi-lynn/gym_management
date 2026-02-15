package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.CategoryCreateRequest;
import com.gymmanagement.gym_management.dtos.CategoryResponse;
import com.gymmanagement.gym_management.dtos.CategoryUpdateRequest;
import com.gymmanagement.gym_management.enums.CategoryStatus;
import com.gymmanagement.gym_management.services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER','MEMBER')")
    public List<CategoryResponse> getActive() {
        return service.getActive();
    }

    @GetMapping("/manage")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<CategoryResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) CategoryStatus status,
            Pageable pageable) {
        return service.search(keyword, status, pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse create(@Valid @RequestBody CategoryCreateRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryUpdateRequest request) {
        return service.update(id, request);
    }
}
