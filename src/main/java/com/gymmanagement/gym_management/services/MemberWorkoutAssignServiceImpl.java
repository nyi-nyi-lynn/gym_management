package com.gymmanagement.gym_management.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.MemberWorkoutAssignRequest;
import com.gymmanagement.gym_management.dtos.MemberWorkoutAssignResponse;
import com.gymmanagement.gym_management.entities.Member;
import com.gymmanagement.gym_management.entities.MemberWorkoutPlanAssign;
import com.gymmanagement.gym_management.entities.WorkoutPlan;
import com.gymmanagement.gym_management.repositories.MemberRepo;
import com.gymmanagement.gym_management.repositories.MemberWorkoutPlanAssignRepo;
import com.gymmanagement.gym_management.repositories.WorkoutPlanRepo;

import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberWorkoutAssignServiceImpl implements MemberWorkoutAssignService {

    private final MemberRepo memberRepo;
    private final WorkoutPlanRepo planRepo;
    private final MemberWorkoutPlanAssignRepo assignRepo;

    @Override
    public MemberWorkoutAssignResponse assignWorkout(MemberWorkoutAssignRequest req) {
        Member member = memberRepo.findById(req.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        WorkoutPlan plan = planRepo.findById(req.getWorkoutPlanId())
                .orElseThrow(() -> new RuntimeException("Workout plan not found"));

        MemberWorkoutPlanAssign assign = MemberWorkoutPlanAssign.builder()
                .member(member)
                .workoutPlan(plan)
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .status(req.getStatus())
                .build();

        return mapToResponse(assignRepo.save(assign));
    }

    @Override
    public MemberWorkoutAssignResponse updateAssignment(Long id, MemberWorkoutAssignRequest req) {
        MemberWorkoutPlanAssign assign = assignRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        assign.setStartDate(req.getStartDate());
        assign.setEndDate(req.getEndDate());
        assign.setStatus(req.getStatus());

        return mapToResponse(assignRepo.save(assign));
    }

    @Override
    public void removeAssignment(Long id) {
        MemberWorkoutPlanAssign assign = assignRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        assignRepo.delete(assign);
    }

    @Override
    public List<MemberWorkoutAssignResponse> getAssignmentsByMember(Long memberId) {
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return assignRepo.findByMember(member)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    //Helper methods 

        private MemberWorkoutAssignResponse mapToResponse(MemberWorkoutPlanAssign assign) {
        return MemberWorkoutAssignResponse.builder()
                .id(assign.getId())
                .memberId(assign.getMember().getId())
                .workoutPlanId(assign.getWorkoutPlan().getId())
                .startDate(assign.getStartDate())
                .endDate(assign.getEndDate())
                .status(assign.getStatus())
                .build();
    }
}
