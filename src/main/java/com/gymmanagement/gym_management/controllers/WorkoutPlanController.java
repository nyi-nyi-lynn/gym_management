package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gymmanagement.gym_management.dtos.WorkoutPlanResponse;
import com.gymmanagement.gym_management.services.WorkoutPlanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workout-plans")
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutPlanService service;

    // ------------------- TRAINER -------------------
    @GetMapping("/trainer/{trainerUserId}")
    public List<WorkoutPlanResponse> getTrainerPlans(@PathVariable Long trainerUserId) {
        return service.getTrainerWorkoutPlans(trainerUserId);
    }

    // ------------------- MEMBER -------------------
    @GetMapping("/member/{memberId}")
    public List<WorkoutPlanResponse> getAssignedPlans(@PathVariable Long memberId) {
        return service.getAssignedWorkoutPlans(memberId);
    }
}
