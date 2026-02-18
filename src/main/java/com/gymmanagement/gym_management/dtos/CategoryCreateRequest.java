package com.gymmanagement.gym_management.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryCreateRequest {
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Size(max = 255)
    private String description;
}
