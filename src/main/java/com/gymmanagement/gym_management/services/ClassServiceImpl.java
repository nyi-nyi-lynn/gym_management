package com.gymmanagement.gym_management.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.ClassRequest;
import com.gymmanagement.gym_management.dtos.ClassResponse;
import com.gymmanagement.gym_management.entities.Category;
import com.gymmanagement.gym_management.entities.ClassEntity;
import com.gymmanagement.gym_management.entities.Trainer;
import com.gymmanagement.gym_management.enums.ClassStatus;
import com.gymmanagement.gym_management.repositories.CategoryRepo;
import com.gymmanagement.gym_management.repositories.ClassRepo;
import com.gymmanagement.gym_management.repositories.TrainerRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassServiceImpl implements ClassService{
    private final ClassRepo classRepo;
    private final CategoryRepo categoryRepo;
    private final TrainerRepo trainerRepo;

    @Override
    public ClassResponse create(ClassRequest request) {

        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Trainer trainer = trainerRepo.findById(request.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        ClassEntity clazz = new ClassEntity();
        clazz.setName(request.getName());
        clazz.setCategory(category);
        clazz.setTrainer(trainer);
        clazz.setPrice(request.getPrice());
        clazz.setCapacity(request.getCapacity());
        clazz.setDuration(request.getDuration());
        clazz.setStatus(ClassStatus.ACTIVE);
        clazz.setCreatedAt(LocalDateTime.now());

        classRepo.save(clazz);

        return mapToResponse(clazz);
    }

    @Override
    public ClassResponse update(Long id, ClassRequest request) {

        ClassEntity clazz = getEntityById(id);

        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Trainer trainer = trainerRepo.findById(request.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        clazz.setName(request.getName());
        clazz.setCategory(category);
        clazz.setTrainer(trainer);
        clazz.setPrice(request.getPrice());
        clazz.setCapacity(request.getCapacity());
        clazz.setDuration(request.getDuration());

        return mapToResponse(clazz);
    }

    @Override
    public void delete(Long id) {
        ClassEntity clazz = getEntityById(id);
        clazz.setStatus(ClassStatus.INACTIVE);
    }

    @Override
    public List<ClassResponse> getAll() {
        return classRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ClassResponse> browseActive() {
        return classRepo.findByStatus(ClassStatus.ACTIVE)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ClassResponse> getTrainerClasses(Long trainerUserId) {

        Trainer trainer = trainerRepo.findByUserId(trainerUserId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        return classRepo.findByTrainerId(trainer.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ClassEntity getEntityById(Long id) {
        return classRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
    }


    //Helper Methods 

    /**
     * 
     * @param clazz
     * @return
     */
    private ClassResponse mapToResponse(ClassEntity clazz) {
       ClassResponse response = new ClassResponse();
        response.setId(clazz.getId());
        response.setName(clazz.getName());
        response.setCategory(clazz.getCategory().getName());
        response.setTrainer(clazz.getTrainer().getUser().getName());
        response.setPrice(clazz.getPrice());
        response.setCapacity(clazz.getCapacity());
        response.setDuration(clazz.getDuration());
        response.setStatus(clazz.getStatus());


       return response;
    }
}
