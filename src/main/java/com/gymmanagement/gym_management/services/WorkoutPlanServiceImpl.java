package com.gymmanagement.gym_management.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.WorkoutPlanRequest;
import com.gymmanagement.gym_management.dtos.WorkoutPlanResponse;
import com.gymmanagement.gym_management.entities.Member;
import com.gymmanagement.gym_management.entities.Trainer;
import com.gymmanagement.gym_management.entities.WorkoutPlan;
import com.gymmanagement.gym_management.entities.MemberWorkoutPlanAssign;
import com.gymmanagement.gym_management.enums.WorkoutPlanStatus;
import com.gymmanagement.gym_management.repositories.MemberRepo;
import com.gymmanagement.gym_management.repositories.TrainerRepo;
import com.gymmanagement.gym_management.repositories.WorkoutPlanRepo;
import com.gymmanagement.gym_management.repositories.MemberWorkoutPlanAssignRepo;

import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

        private final WorkoutPlanRepo planRepo;
        private final TrainerRepo trainerRepo;
        private final MemberRepo memberRepo;
        private final MemberWorkoutPlanAssignRepo assignRepo;

        @Override
        public WorkoutPlanResponse createWorkoutPlan(WorkoutPlanRequest request) {
                Trainer trainer = trainerRepo.findById(request.getTrainerId())
                                .orElseThrow(() -> new RuntimeException("Trainer not found"));

                WorkoutPlan plan = new WorkoutPlan();
                plan.setTrainer(trainer);
                plan.setTitle(request.getTitle());
                plan.setDescription(request.getDescription());
                plan.setStatus(request.getStatus() != null ? request.getStatus() : WorkoutPlanStatus.ACTIVE);
                planRepo.save(plan);

                return mapToResponse(planRepo.save(plan));
        }

        @Override
        public WorkoutPlanResponse updateWorkoutPlan(Long planId, WorkoutPlanRequest request) {
                WorkoutPlan plan = planRepo.findById(planId)
                                .orElseThrow(() -> new RuntimeException("Workout plan not found"));

                plan.setTitle(request.getTitle());
                plan.setDescription(request.getDescription());
                if (request.getStatus() != null)
                        plan.setStatus(request.getStatus());

                return mapToResponse(planRepo.save(plan));
        }

        @Override
        public void deleteWorkoutPlan(Long planId) {
                WorkoutPlan plan = planRepo.findById(planId)
                                .orElseThrow(() -> new RuntimeException("Workout plan not found"));
                planRepo.delete(plan);
        }

        @Override
        public List<WorkoutPlanResponse> getAllWorkoutPlans() {
                return planRepo.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
        }

        @Override
        public List<WorkoutPlanResponse> getTrainerWorkoutPlans(Long trainerUserId) {
                Trainer trainer = trainerRepo.findByUserId(trainerUserId)
                                .orElseThrow(() -> new RuntimeException("Trainer not found"));

                return planRepo.findByTrainer(trainer)
                                .stream()
                                .map(this::mapToResponse)
                                .collect(Collectors.toList());
        }

        @Override
        public List<WorkoutPlanResponse> getAssignedWorkoutPlans(Long memberId) {
                Member member = memberRepo.findById(memberId)
                                .orElseThrow(() -> new RuntimeException("Member not found"));

                List<MemberWorkoutPlanAssign> assigns = assignRepo.findByMember(member);

                return assigns.stream()
                                .map(assign -> mapToResponse(assign.getWorkoutPlan()))
                                .collect(Collectors.toList());
        }

        // Helper methods

        private WorkoutPlanResponse mapToResponse(WorkoutPlan plan) {
                return WorkoutPlanResponse.builder()
                                .id(plan.getId())
                                .title(plan.getTitle())
                                .description(plan.getDescription())
                                .trainerName(plan.getTrainer().getUser().getName())
                                .status(plan.getStatus())
                                .build();
        }
}
