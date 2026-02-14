package com.gymmanagement.gym_management.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.WorkoutScheduleRequest;
import com.gymmanagement.gym_management.dtos.WorkoutScheduleResponse;
import com.gymmanagement.gym_management.entities.WorkoutPlan;
import com.gymmanagement.gym_management.entities.WorkoutSchedule;
import com.gymmanagement.gym_management.repositories.WorkoutPlanRepo;
import com.gymmanagement.gym_management.repositories.WorkoutScheduleRepo;

import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkoutScheduleServiceImpl implements WorkoutScheduleService {

    private final WorkoutScheduleRepo scheduleRepo;
    private final WorkoutPlanRepo planRepo;

    private WorkoutScheduleResponse mapToResponse(WorkoutSchedule schedule) {
        return WorkoutScheduleResponse.builder()
                .id(schedule.getId())
                .workoutPlanId(schedule.getWorkoutPlan().getId())
                .dayOfWeek(schedule.getDayOfWeek())
                .exercise(schedule.getExercise())
                .sets(schedule.getSets())
                .reps(schedule.getReps())
                .build();
    }

    @Override
    public WorkoutScheduleResponse createSchedule(WorkoutScheduleRequest req) {
        WorkoutPlan plan = planRepo.findById(req.getWorkoutPlanId())
                .orElseThrow(() -> new RuntimeException("Workout plan not found"));

        WorkoutSchedule schedule = WorkoutSchedule.builder()
                .workoutPlan(plan)
                .dayOfWeek(req.getDayOfWeek())
                .exercise(req.getExercise())
                .sets(req.getSets())
                .reps(req.getReps())
                .build();

        return mapToResponse(scheduleRepo.save(schedule));
    }

    @Override
    public WorkoutScheduleResponse updateSchedule(Long id, WorkoutScheduleRequest req) {
        WorkoutSchedule schedule = scheduleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        schedule.setDayOfWeek(req.getDayOfWeek());
        schedule.setExercise(req.getExercise());
        schedule.setSets(req.getSets());
        schedule.setReps(req.getReps());

        return mapToResponse(scheduleRepo.save(schedule));
    }

    @Override
    public void deleteSchedule(Long id) {
        WorkoutSchedule schedule = scheduleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        scheduleRepo.delete(schedule);
    }

    @Override
    public List<WorkoutScheduleResponse> getSchedulesByPlan(Long workoutPlanId) {
        WorkoutPlan plan = planRepo.findById(workoutPlanId)
                .orElseThrow(() -> new RuntimeException("Workout plan not found"));

        return scheduleRepo.findByWorkoutPlan(plan)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
