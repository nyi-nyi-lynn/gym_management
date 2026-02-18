package com.gymmanagement.gym_management.dtos;

import java.time.LocalDate;

import com.gymmanagement.gym_management.enums.AssignStatus;

import lombok.Data;

@Data
public class MemberWorkoutAssignRequest {
    private Long memberId;
    private Long workoutPlanId;
    private LocalDate startDate;
    private LocalDate endDate;
    private AssignStatus status;
}
