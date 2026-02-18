package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.WorkoutScheduleRequest;
import com.gymmanagement.gym_management.dtos.WorkoutScheduleResponse;

public interface WorkoutScheduleService {

    // ADMIN / TRAINER
    WorkoutScheduleResponse createSchedule(WorkoutScheduleRequest req);
    WorkoutScheduleResponse updateSchedule(Long id, WorkoutScheduleRequest req);
    void deleteSchedule(Long id);
    List<WorkoutScheduleResponse> getSchedulesByPlan(Long workoutPlanId);
}
