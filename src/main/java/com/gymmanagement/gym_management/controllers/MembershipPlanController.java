package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.MembershipPlanRequestDTO;
import com.gymmanagement.gym_management.dtos.MembershipPlanResponseDTO;
import com.gymmanagement.gym_management.services.MembershipPlanService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/plans")
public class MembershipPlanController {
    @Autowired
    private MembershipPlanService service;

    @PostMapping
    public MembershipPlanResponseDTO createPlan(@Valid @RequestBody MembershipPlanRequestDTO dto) {
        return service.createPlan(dto);
    }

    @GetMapping
    public List<MembershipPlanResponseDTO> getAllPlan() {
        return service.getAllPlans();
    }
    
    
}
