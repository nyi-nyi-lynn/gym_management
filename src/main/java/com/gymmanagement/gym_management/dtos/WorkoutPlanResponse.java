package com.gymmanagement.gym_management.dtos;

import com.gymmanagement.gym_management.enums.WorkoutPlanStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkoutPlanResponse {
    private Long id;
    private String title;
    private String description;
    private String trainerName;
    private WorkoutPlanStatus status;
}
