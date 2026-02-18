package com.gymmanagement.gym_management.dtos;

import java.math.BigDecimal;

import com.gymmanagement.gym_management.enums.CourseLevel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CourseRequest {
    @NotNull
    private Long categoryId;

    private Long trainerId;

    @NotBlank
    private String title;

    private String description;

    @NotNull @Positive
    private BigDecimal price;

    @NotNull @Positive
    private Integer durationDays;

    @NotNull
    private CourseLevel level;
}
