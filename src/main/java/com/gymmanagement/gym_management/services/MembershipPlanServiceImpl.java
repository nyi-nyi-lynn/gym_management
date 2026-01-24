package com.gymmanagement.gym_management.services;

import java.lang.reflect.Member;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.MembershipPlanRequestDTO;
import com.gymmanagement.gym_management.dtos.MembershipPlanResponseDTO;
import com.gymmanagement.gym_management.entities.MembershipPlan;
import com.gymmanagement.gym_management.repositories.MembershipPlanRepo;

@Service
public class MembershipPlanServiceImpl implements MembershipPlanService {

    @Autowired
    private MembershipPlanRepo membershipPlanRepo;

    @Override
    public MembershipPlanResponseDTO createPlan(MembershipPlanRequestDTO dto) {
       MembershipPlan plan = new MembershipPlan();
       plan.setPlan_name(dto.getPlan_name());
       plan.setPrice(dto.getPrice());
       plan.setDurationMonths(dto.getDurationMonths());

       MembershipPlan savedPlan = membershipPlanRepo.save(plan);

       MembershipPlanResponseDTO response = new MembershipPlanResponseDTO();
       response.setId(savedPlan.getId());
       response.setPlan_name(savedPlan.getPlan_name());
       response.setPrice(savedPlan.getPrice());
       response.setDurationMonths(savedPlan.getDurationMonths());
       response.setActive(savedPlan.isActive());

       return response;
    }

    @Override
    public List<MembershipPlanResponseDTO> getAllPlans() {;
       return membershipPlanRepo.findAll()
       .stream()
       .map(plan -> {
              MembershipPlanResponseDTO dto = new MembershipPlanResponseDTO();
              dto.setId(plan.getId());
              dto.setPlan_name(plan.getPlan_name());
              dto.setPrice(plan.getPrice());
              dto.setDurationMonths(plan.getDurationMonths());
              dto.setActive(plan.isActive());
              return dto;
       }).collect(Collectors.toList());
        
    }
}
