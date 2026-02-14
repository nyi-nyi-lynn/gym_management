package com.gymmanagement.gym_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.TrainerProfileRequest;
import com.gymmanagement.gym_management.dtos.TrainerResponse;
import com.gymmanagement.gym_management.services.TrainerService;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {
    @Autowired
    private TrainerService  trainerService;

       // Trainer self
    @PutMapping("/profile")
    public TrainerResponse completeProfile(
            @RequestParam Long userId,
            @RequestBody TrainerProfileRequest request) {
        return trainerService.completeProfile(userId, request);
    }

    @GetMapping("/me")
    public TrainerResponse getMyProfile(@RequestParam Long userId) {
        return trainerService.getTrainerByUserId(userId);
    }
}
