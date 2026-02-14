package com.gymmanagement.gym_management.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.CategoryCreateRequest;
import com.gymmanagement.gym_management.dtos.CategoryResponse;
import com.gymmanagement.gym_management.dtos.CategoryUpdateRequest;
import com.gymmanagement.gym_management.entities.Category;
import com.gymmanagement.gym_management.enums.CategoryStatus;
import com.gymmanagement.gym_management.repositories.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
        @Autowired
        private CategoryRepo categoryRepo;

        @Override
        public CategoryResponse create(CategoryCreateRequest request) {
            if (categoryRepo.existsByNameIgnoreCase(request.getName())) {
                throw new RuntimeException("Category name already exists");
            }

            Category category = new Category();
            category.setName(request.getName().trim());
            category.setDescription(request.getDescription());
            category.setStatus(CategoryStatus.ACTIVE);
            category.setCreatedAt(LocalDateTime.now());

            return mapToResponse(categoryRepo.save(category));
        }

        @Override
        public CategoryResponse update(Long id, CategoryUpdateRequest request) {
            Category category = getCategory(id);

        if (!category.getName().equalsIgnoreCase(request.getName())
            && categoryRepo.existsByNameIgnoreCase(request.getName())) {
            throw new RuntimeException("Category name already exists");
        }

        category.setName(request.getName().trim());
        category.setDescription(request.getDescription());
        category.setStatus(CategoryStatus.valueOf(request.getStatus().toUpperCase()));

        return mapToResponse(categoryRepo.save(category));
        }

        @Override
        public Page<CategoryResponse> search(String keyword, CategoryStatus status, Pageable pageable) {
            Page<Category> categoryPage = categoryRepo.search(
            keyword == null ? null : keyword.trim(),
            status,
            pageable);
             return categoryPage.map(this::mapToResponse);
        }

        @Override
        public List<CategoryResponse> getActive() {
            return categoryRepo.findByStatus(CategoryStatus.ACTIVE)
            .stream()
            .map(this::mapToResponse)
            .toList();
        }

        @Override
        public void deactivate(Long id) {
             Category category = getCategory(id);

            category.setStatus(CategoryStatus.INACTIVE);
            categoryRepo.save(category);
        }


        //Helper Methods

        /**
         * Map to Response
         * @param category
         * @return
         */
        public CategoryResponse mapToResponse(Category category){
            CategoryResponse response = new CategoryResponse();
            response.setId(category.getId());
            response.setName(category.getName());
            response.setDescription(category.getDescription());
            response.setStatus(category.getStatus());
            response.setCreatedAt(category.getCreatedAt());

            return response;
        }

        /**
         * to find category with id
         * @param id
         * @return
         */
        public Category getCategory(Long id){
            return categoryRepo.findById(id)
            .orElseThrow(()-> new RuntimeException("category not found"));
        }
}
