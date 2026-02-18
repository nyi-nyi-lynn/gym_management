package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.Trainer;
import com.gymmanagement.gym_management.entities.WorkoutPlan;

@Repository
public interface WorkoutPlanRepo extends JpaRepository<WorkoutPlan,Long>{
    List<WorkoutPlan> findByTrainer(Trainer trainer);
}
