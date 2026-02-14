package com.gymmanagement.gym_management.controllers;

import java.util.List;

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
@RequestMapping("/api/admin/workout-plans")
@RequiredArgsConstructor
public class AdminWorkoutPlanController {   

    private final WorkoutPlanService service;

    
    @GetMapping
    public List<WorkoutPlanResponse> getAll() {
        return service.getAllWorkoutPlans();
    }

    @PostMapping
    public WorkoutPlanResponse create(@RequestBody WorkoutPlanRequest req) {
        return service.createWorkoutPlan(req);
    }

    @PutMapping("/{planId}")
    public WorkoutPlanResponse update(@PathVariable Long planId, @RequestBody WorkoutPlanRequest req) {
        return service.updateWorkoutPlan(planId, req);
    }

    
    @DeleteMapping("/{planId}")
    public String delete(@PathVariable Long planId) {
        service.deleteWorkoutPlan(planId);
        return "Delete Successful";
    }

}
