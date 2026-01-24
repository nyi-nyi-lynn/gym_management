package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.MembershipPlanRequestDTO;
import com.gymmanagement.gym_management.dtos.MembershipPlanResponseDTO;

public interface MembershipPlanService {
    MembershipPlanResponseDTO createPlan(MembershipPlanRequestDTO dto);

    List<MembershipPlanResponseDTO> getAllPlans();
}
