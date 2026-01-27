package com.gymmanagement.gym_management.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkoutPlanRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Day is required")
    private String day;

    @NotNull(message = "Trainer Id is required")
    private Long trainerId;

    @NotNull(message = "Member Id is required")
    private Long memberId;
}
