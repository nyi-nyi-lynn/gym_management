package com.gymmanagement.gym_management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.TrainerProfileRequest;
import com.gymmanagement.gym_management.dtos.TrainerResponse;
import com.gymmanagement.gym_management.entities.Trainer;
import com.gymmanagement.gym_management.enums.EmploymentStatus;
import com.gymmanagement.gym_management.repositories.TrainerRepo;

@Service
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepo trainerRepo;

    @Override
    public TrainerResponse completeProfile(Long userId, TrainerProfileRequest request) {
        Trainer trainer = getTrainer(userId);

        trainer.setSpecialization(request.getSpecialization());
        trainer.setExperienceYears(request.getExperienceYears());
        trainer.setBio(request.getBio());
        trainer.setProfileCompleted(true);
        trainerRepo.save(trainer);

        return mapToResponse(trainer);
    }

    @Override
    public List<TrainerResponse> getAllActiveTrainers() {
        return trainerRepo.findByStatus(EmploymentStatus.ACTIVE)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TrainerResponse getTrainerByUserId(Long userId) {
        return trainerRepo.findByUserId(userId)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
    }



    //Helper Method
    private Trainer getTrainer(Long userId) {
        return trainerRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
    }

    private TrainerResponse mapToResponse(Trainer trainer) {
        TrainerResponse response = new TrainerResponse();
        response.setTrainerId(trainer.getId());
        response.setUserId(trainer.getUser().getId());
        response.setName(trainer.getUser().getName());
        response.setEmail(trainer.getUser().getEmail());
        response.setSpecialization(trainer.getSpecialization());
        response.setExperienceYears(trainer.getExperienceYears());
        response.setBio(trainer.getBio());
        response.setStartWorkDate(trainer.getStartWorkDate());
        response.setStatus(trainer.getStatus());

        return response;
    }    

}
