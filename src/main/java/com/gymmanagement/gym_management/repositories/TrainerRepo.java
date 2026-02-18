package com.gymmanagement.gym_management.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.Trainer;
import com.gymmanagement.gym_management.enums.EmploymentStatus;

@Repository
public interface TrainerRepo extends JpaRepository<Trainer,Long> {
    Optional<Trainer> findByUserId (Long userId);

     List<Trainer> findByStatus(EmploymentStatus status);
}
