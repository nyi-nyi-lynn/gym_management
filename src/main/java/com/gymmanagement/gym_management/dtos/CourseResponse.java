package com.gymmanagement.gym_management.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gymmanagement.gym_management.enums.CourseLevel;
import com.gymmanagement.gym_management.enums.CourseStatus;

import lombok.Data;


@Data
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer durationDays;
    private CourseLevel level;
    private String thumbnailUrl;
    private CourseStatus status;
    private LocalDateTime createdAt;

    private Long categoryId;
    private String categoryName;

    private Long trainerId;
}
