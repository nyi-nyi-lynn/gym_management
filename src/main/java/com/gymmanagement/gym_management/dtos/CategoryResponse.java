package com.gymmanagement.gym_management.dtos;

import java.time.LocalDateTime;

import com.gymmanagement.gym_management.enums.CategoryStatus;

import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private CategoryStatus status;
    private LocalDateTime createdAt;
}
