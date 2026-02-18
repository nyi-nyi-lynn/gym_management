package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.WorkoutPlan;
import com.gymmanagement.gym_management.entities.WorkoutSchedule;

@Repository
public interface WorkoutScheduleRepo extends JpaRepository<WorkoutSchedule, Long> {
    List<WorkoutSchedule> findByWorkoutPlan(WorkoutPlan plan);
}
