package com.gymmanagement.gym_management.dtos;

import com.gymmanagement.gym_management.enums.ContentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseContentRequest {
    @NotNull
    private Long courseId;

    @NotBlank
    private String title;

    @NotNull
    private ContentType contentType;

    private String contentUrl;
}
