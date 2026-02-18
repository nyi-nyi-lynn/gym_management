package com.gymmanagement.gym_management.dtos;

import com.gymmanagement.gym_management.enums.DayOfWeek;
import lombok.Data;

@Data
public class WorkoutScheduleRequest {
    private Long workoutPlanId;
    private DayOfWeek dayOfWeek;
    private String exercise;
    private Integer sets;
    private Integer reps;
}
