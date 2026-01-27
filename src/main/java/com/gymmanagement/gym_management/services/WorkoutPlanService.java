package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.WorkoutPlanRequestDTO;
import com.gymmanagement.gym_management.entities.WorkoutPlan;

public interface WorkoutPlanService {
    public WorkoutPlan createPlan(WorkoutPlanRequestDTO workoutPlan);

    public List<WorkoutPlan> getPlansForMember(Long memberId);
}
