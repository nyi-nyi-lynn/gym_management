package com.gymmanagement.gym_management.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gymmanagement.gym_management.dtos.MembershipSubscriptionResponse;
import com.gymmanagement.gym_management.entities.Member;
import com.gymmanagement.gym_management.entities.MembershipPlan;
import com.gymmanagement.gym_management.entities.MembershipSubscription;
import com.gymmanagement.gym_management.entities.Order;
import com.gymmanagement.gym_management.enums.SubscriptionStatus;
import com.gymmanagement.gym_management.repositories.MemberRepo;
import com.gymmanagement.gym_management.repositories.MembershipPlanRepo;
import com.gymmanagement.gym_management.repositories.MembershipSubscriptionRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MembershipSubscriptionServiceImpl implements MembershipSubscriptionService {

    private final MembershipSubscriptionRepo subscriptionRepo;
    private final MemberRepo memberRepo;
    private final MembershipPlanRepo planRepo;

    @Override
    public MembershipSubscriptionResponse subscribe(Order order) {
        Member member = memberRepo.findById(order.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        MembershipPlan plan = planRepo.findById(order.getReferenceId())
                .orElseThrow(() -> new RuntimeException("Membership plan not found"));

        MembershipSubscription subscription = new MembershipSubscription();
        subscription.setOrder(order);
        subscription.setMember(member);
        subscription.setMembershipPlan(plan);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(plan.getDurationMonths()));
        subscription.setStatus(SubscriptionStatus.ACTIVE);

        MembershipSubscription saved = subscriptionRepo.save(subscription);

        return mapToResponse(saved);
    }

    @Override
    public List<MembershipSubscriptionResponse> getMemberSubscriptions(Long memberId) {
        List<MembershipSubscription> list = subscriptionRepo.findByMemberId(memberId);
        return list.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Long id, SubscriptionStatus status) {
        MembershipSubscription subscription = subscriptionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        subscription.setStatus(status);
        subscriptionRepo.save(subscription);
    }

    private MembershipSubscriptionResponse mapToResponse(MembershipSubscription s) {
        MembershipSubscriptionResponse res = new MembershipSubscriptionResponse();
        res.setId(s.getId());
        res.setMemberId(s.getMember().getId());
        res.setMembershipPlanId(s.getMembershipPlan().getId());
        res.setStartDate(s.getStartDate());
        res.setEndDate(s.getEndDate());
        res.setStatus(s.getStatus());
        return res;
    }
}
