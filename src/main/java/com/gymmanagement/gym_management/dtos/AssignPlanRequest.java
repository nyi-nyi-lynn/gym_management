package com.gymmanagement.gym_management.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AssignPlanRequest {
    private Long memberId;
    private Long workoutPlanId;
    private LocalDate startDate;
    private Integer durationMonths;
}
