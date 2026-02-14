package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.TrainerProfileRequest;
import com.gymmanagement.gym_management.dtos.TrainerResponse;

public interface TrainerService {
    // Profile
    TrainerResponse completeProfile(Long userId, TrainerProfileRequest request);
    TrainerResponse getTrainerByUserId(Long userId);
    List<TrainerResponse> getAllActiveTrainers();

}
