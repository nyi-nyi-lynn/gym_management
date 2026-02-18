package com.gymmanagement.gym_management.dtos;

import com.gymmanagement.gym_management.enums.WorkoutPlanStatus;

import lombok.Data;

@Data
public class WorkoutPlanRequest {
    private Long trainerId;
    private String title;
    private String description;
    private WorkoutPlanStatus status;
}
