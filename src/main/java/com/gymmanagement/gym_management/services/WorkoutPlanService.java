package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.WorkoutPlanRequest;
import com.gymmanagement.gym_management.dtos.WorkoutPlanResponse;

public interface WorkoutPlanService {
     // ADMIN / TRAINER
    WorkoutPlanResponse createWorkoutPlan(WorkoutPlanRequest request);
    WorkoutPlanResponse updateWorkoutPlan(Long planId, WorkoutPlanRequest request);
    void deleteWorkoutPlan(Long planId);
    List<WorkoutPlanResponse> getAllWorkoutPlans(); // Admin view all

    // TRAINER
    List<WorkoutPlanResponse> getTrainerWorkoutPlans(Long trainerUserId);

    // MEMBER
    List<WorkoutPlanResponse> getAssignedWorkoutPlans(Long memberId);
}
