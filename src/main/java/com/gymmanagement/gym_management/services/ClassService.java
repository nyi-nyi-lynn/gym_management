package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.ClassRequest;
import com.gymmanagement.gym_management.dtos.ClassResponse;
import com.gymmanagement.gym_management.entities.ClassEntity;

public interface ClassService {
     // ADMIN
    ClassResponse create(ClassRequest request);
    ClassResponse update(Long id, ClassRequest request);
    void delete(Long id);
    List<ClassResponse> getAll();

    // MEMBER
    List<ClassResponse> browseActive();

    // TRAINER
    List<ClassResponse> getTrainerClasses(Long trainerUserId);

    ClassEntity getEntityById(Long id);
}
