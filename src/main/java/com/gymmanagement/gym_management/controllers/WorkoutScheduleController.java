package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gymmanagement.gym_management.dtos.WorkoutScheduleRequest;
import com.gymmanagement.gym_management.dtos.WorkoutScheduleResponse;
import com.gymmanagement.gym_management.services.WorkoutScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workout-schedules")
@RequiredArgsConstructor
public class WorkoutScheduleController {

    private final WorkoutScheduleService service;

    @PostMapping
    public WorkoutScheduleResponse create(@RequestBody WorkoutScheduleRequest req) {
        return service.createSchedule(req);
    }

    @PutMapping("/{id}")
    public WorkoutScheduleResponse update(@PathVariable Long id, @RequestBody WorkoutScheduleRequest req) {
        return service.updateSchedule(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteSchedule(id);
    }

    @GetMapping("/plan/{planId}")
    public List<WorkoutScheduleResponse> getByPlan(@PathVariable Long planId) {
        return service.getSchedulesByPlan(planId);
    }
}
