package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.WorkoutPlanRequestDTO;
import com.gymmanagement.gym_management.entities.WorkoutPlan;
import com.gymmanagement.gym_management.services.WorkoutPlanService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/workout-plans")
public class WorkoutPlanController {
    @Autowired
    private WorkoutPlanService service;

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/member/{memberId}")
    public List<WorkoutPlan> viewPlan(@Valid @PathVariable Long memberId) {
        return service.getPlansForMember(memberId);
    }


    @PreAuthorize("hasRole('TRAINER')")
    @PostMapping("/trainer")
    public WorkoutPlan createPlan(@Valid @RequestBody WorkoutPlanRequestDTO dto) {
       
        return service.createPlan(dto);
    }
    
    
}
