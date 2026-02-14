package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.MembershipPlanRequest;
import com.gymmanagement.gym_management.dtos.MembershipPlanResponse;

public interface MembershipPlanService {
    MembershipPlanResponse create(MembershipPlanRequest req);

    MembershipPlanResponse update(Long id, MembershipPlanRequest req);

    void delete(Long id);

    List<MembershipPlanResponse> getAll();
}
