package com.gymmanagement.gym_management.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.MembershipPlanRequest;
import com.gymmanagement.gym_management.dtos.MembershipPlanResponse;
import com.gymmanagement.gym_management.entities.MembershipPlan;
import com.gymmanagement.gym_management.enums.PlanStatus;
import com.gymmanagement.gym_management.repositories.MembershipPlanRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembershipPlanServiceImpl implements MembershipPlanService {

    private final MembershipPlanRepo repo;

    // ================================
    // CREATE
    // ================================
    @Override
    public MembershipPlanResponse create(MembershipPlanRequest req) {

        MembershipPlan plan = new MembershipPlan();
        plan.setName(req.getName());
        plan.setPrice(req.getPrice());
        plan.setDurationMonths(req.getDurationMonths());
        plan.setDescription(req.getDescription());
        plan.setStatus(PlanStatus.ACTIVE);

        repo.save(plan);

        return mapToResponse(plan);
    }

    // ================================
    // UPDATE
    // ================================
    @Override
    public MembershipPlanResponse update(Long id, MembershipPlanRequest req) {

        MembershipPlan plan = repo.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Membership Plan not found with id: " + id));

        plan.setName(req.getName());
        plan.setPrice(req.getPrice());
        plan.setDurationMonths(req.getDurationMonths());
        plan.setDescription(req.getDescription());

        repo.save(plan);

        return mapToResponse(plan);
    }

    // ================================
    // DELETE
    // ================================
    @Override
    public void delete(Long id) {

        MembershipPlan plan = repo.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Membership Plan not found with id: " + id));

        repo.delete(plan);
    }

    // ================================
    // GET ALL
    // ================================
    @Override
    public List<MembershipPlanResponse> getAll() {

        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ================================
    // MAPPER
    // ================================
    private MembershipPlanResponse mapToResponse(MembershipPlan plan) {

        MembershipPlanResponse res = new MembershipPlanResponse();

        res.setId(plan.getId());
        res.setName(plan.getName());
        res.setPrice(plan.getPrice());
        res.setDurationMonths(plan.getDurationMonths());
        res.setDescription(plan.getDescription());
        res.setStatus(plan.getStatus());

        return res;
    }
}
