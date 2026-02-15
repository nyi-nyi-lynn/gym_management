package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.WorkoutPlanRequest;
import com.gymmanagement.gym_management.dtos.WorkoutPlanResponse;
import com.gymmanagement.gym_management.services.WorkoutPlanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workout-plans")
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutPlanService service;

    @GetMapping("/manage")
    @PreAuthorize("hasRole('ADMIN')")
    public List<WorkoutPlanResponse> getAll() {
        return service.getAllWorkoutPlans();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public WorkoutPlanResponse create(@RequestBody WorkoutPlanRequest req) {
        return service.createWorkoutPlan(req);
    }

    @PutMapping("/{planId}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public WorkoutPlanResponse update(
            @PathVariable Long planId,
            @RequestBody WorkoutPlanRequest req) {
        return service.updateWorkoutPlan(planId, req);
    }

    @DeleteMapping("/{planId}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public String delete(@PathVariable Long planId) {
        service.deleteWorkoutPlan(planId);
        return "Delete Successful";
    }

    @GetMapping("/trainers/{trainerUserId}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public List<WorkoutPlanResponse> getTrainerPlans(@PathVariable Long trainerUserId) {
        return service.getTrainerWorkoutPlans(trainerUserId);
    }

    @GetMapping("/members/{memberId}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER','MEMBER')")
    public List<WorkoutPlanResponse> getAssignedPlans(@PathVariable Long memberId) {
        return service.getAssignedWorkoutPlans(memberId);
    }
}
