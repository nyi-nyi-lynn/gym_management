package com.gymmanagement.gym_management.dtos;

import com.gymmanagement.gym_management.enums.ContentType;

import lombok.Data;

@Data
public class CourseContentResponse {
    private Long id;
    private Long courseId;
    private String title;
    private ContentType contentType;
    private String contentUrl;
}
