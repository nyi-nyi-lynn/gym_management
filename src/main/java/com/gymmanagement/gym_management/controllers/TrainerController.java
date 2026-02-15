package com.gymmanagement.gym_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.TrainerProfileRequest;
import com.gymmanagement.gym_management.dtos.TrainerResponse;
import com.gymmanagement.gym_management.security.CustomUserDetails;
import com.gymmanagement.gym_management.services.TrainerService;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {
    @Autowired
    private TrainerService  trainerService;

       // Trainer self
    @PutMapping("/me/profile")
    @PreAuthorize("hasRole('TRAINER')")
    public TrainerResponse completeProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody TrainerProfileRequest request) {
        return trainerService.completeProfile(userDetails.getId(), request);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('TRAINER')")
    public TrainerResponse getMyProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return trainerService.getTrainerByUserId(userDetails.getId());
    }
}
