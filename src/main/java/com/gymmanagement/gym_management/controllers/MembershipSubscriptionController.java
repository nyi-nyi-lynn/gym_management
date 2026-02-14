package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gymmanagement.gym_management.dtos.MembershipSubscriptionResponse;
import com.gymmanagement.gym_management.enums.SubscriptionStatus;
import com.gymmanagement.gym_management.services.MembershipSubscriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/membership-subscriptions")
@RequiredArgsConstructor
public class MembershipSubscriptionController {

    private final MembershipSubscriptionService service;


    @GetMapping("/member/{memberId}")
    public List<MembershipSubscriptionResponse> getMemberSubscriptions(@PathVariable Long memberId) {
        return service.getMemberSubscriptions(memberId);
    }

    // ------------------- ADMIN -------------------
    @PutMapping("/admin/{subscriptionId}/status")
    public void updateStatus(@PathVariable Long subscriptionId,
                             @RequestParam SubscriptionStatus status) {
        service.updateStatus(subscriptionId, status);
    }
}
