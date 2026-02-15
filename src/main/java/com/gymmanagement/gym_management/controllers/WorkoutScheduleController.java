package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gymmanagement.gym_management.dtos.WorkoutScheduleRequest;
import com.gymmanagement.gym_management.dtos.WorkoutScheduleResponse;
import com.gymmanagement.gym_management.services.WorkoutScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkoutScheduleController {

    private final WorkoutScheduleService service;

    @PostMapping("/workout-plans/{planId}/schedules")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public WorkoutScheduleResponse create(
            @PathVariable Long planId,
            @RequestBody WorkoutScheduleRequest req) {
        req.setWorkoutPlanId(planId);
        return service.createSchedule(req);
    }

    @PutMapping("/workout-schedules/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public WorkoutScheduleResponse update(@PathVariable Long id, @RequestBody WorkoutScheduleRequest req) {
        return service.updateSchedule(id, req);
    }

    @DeleteMapping("/workout-schedules/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public void delete(@PathVariable Long id) {
        service.deleteSchedule(id);
    }

    @GetMapping("/workout-plans/{planId}/schedules")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER','MEMBER')")
    public List<WorkoutScheduleResponse> getByPlan(@PathVariable Long planId) {
        return service.getSchedulesByPlan(planId);
    }
}
