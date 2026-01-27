package com.gymmanagement.gym_management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.WorkoutPlanRequestDTO;
import com.gymmanagement.gym_management.entities.User;
import com.gymmanagement.gym_management.entities.WorkoutPlan;
import com.gymmanagement.gym_management.repositories.UserRepo;
import com.gymmanagement.gym_management.repositories.WorkoutPlanRepo;



@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService{
    @Autowired
    WorkoutPlanRepo workoutPlanRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public WorkoutPlan createPlan(WorkoutPlanRequestDTO dto) {
        User trainer = userRepo.findById(dto.getTrainerId())
        .orElseThrow(()-> new RuntimeException("Trainer Not Found"));

        User member = userRepo.findById(dto.getMemberId())
        .orElseThrow(()-> new RuntimeException("Member Not Found"));

        WorkoutPlan plan = new WorkoutPlan();
        plan.setTitle(dto.getTitle());
        plan.setDecription(dto.getDescription());
        plan.setDay(dto.getDay());
        plan.setTrainer(trainer);
        plan.setMember(member);
        workoutPlanRepo.save(plan);
        
        return plan;
    }

    @Override
    public List<WorkoutPlan> getPlansForMember(Long memberId) {
        return workoutPlanRepo.findByMemberId(memberId);
    }
}
