package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.ClassEntity;
import com.gymmanagement.gym_management.enums.ClassStatus;

@Repository
public interface ClassRepo extends JpaRepository<ClassEntity,Long> {
     List<ClassEntity> findByStatus(ClassStatus status);

    List<ClassEntity> findByTrainerId(Long trainerId);
}
