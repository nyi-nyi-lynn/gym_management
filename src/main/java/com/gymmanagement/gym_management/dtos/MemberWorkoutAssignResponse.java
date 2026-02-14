package com.gymmanagement.gym_management.dtos;

import java.time.LocalDate;

import com.gymmanagement.gym_management.enums.AssignStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberWorkoutAssignResponse {
    private Long id;
    private Long memberId;
    private Long workoutPlanId;
    private LocalDate startDate;
    private LocalDate endDate;
    private AssignStatus status;
}
