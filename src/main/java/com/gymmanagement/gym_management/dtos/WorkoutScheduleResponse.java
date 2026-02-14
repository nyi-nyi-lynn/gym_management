package com.gymmanagement.gym_management.dtos;

import com.gymmanagement.gym_management.enums.DayOfWeek;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkoutScheduleResponse {
    private Long id;
    private Long workoutPlanId;
    private DayOfWeek dayOfWeek;
    private String exercise;
    private Integer sets;
    private Integer reps;
}
