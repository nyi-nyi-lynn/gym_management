package com.gymmanagement.gym_management.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.SubscribeRequestDTO;
import com.gymmanagement.gym_management.entities.MemberSubscription;
import com.gymmanagement.gym_management.entities.MembershipPlan;
import com.gymmanagement.gym_management.entities.User;
import com.gymmanagement.gym_management.repositories.MemberSubscriptionRepo;
import com.gymmanagement.gym_management.repositories.MembershipPlanRepo;
import com.gymmanagement.gym_management.repositories.UserRepo;

@Service
public class MemberSubscriptionServiceImpl implements MemberSubscriptionService {

    @Autowired
    private UserRepo userRepo ;

    @Autowired
    private MembershipPlanRepo planRepo;

    @Autowired
    private MemberSubscriptionRepo subscriptionRepo ;

    @Override
    public void subscribe(SubscribeRequestDTO dto) {
        User member = userRepo.findById(dto.getMemberId())
        .orElseThrow(()-> new RuntimeException("Member not found"));

        MembershipPlan plan = planRepo.findById(dto.getPlanId())
        .orElseThrow(()-> new RuntimeException("Plan not found"));

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(plan.getDurationMonths());

        MemberSubscription subscription = new MemberSubscription();
        subscription.setMember(member);
        subscription.setPlan(plan);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);
        subscription.setActive(true);

        subscriptionRepo.save(subscription);

    }
}
