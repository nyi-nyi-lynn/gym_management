package com.gymmanagement.gym_management.dtos;

import java.time.LocalDate;

import com.gymmanagement.gym_management.enums.SubscriptionStatus;

import lombok.Data;

@Data
public class MembershipSubscriptionResponse {
    private Long id;
    private Long memberId;
    private Long membershipPlanId;
    private LocalDate startDate;
    private LocalDate endDate;
    private SubscriptionStatus status;
}
