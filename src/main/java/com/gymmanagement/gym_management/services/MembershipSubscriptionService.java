package com.gymmanagement.gym_management.services;

import java.util.List;


import com.gymmanagement.gym_management.dtos.MembershipSubscriptionResponse;
import com.gymmanagement.gym_management.entities.Orders;
import com.gymmanagement.gym_management.enums.SubscriptionStatus;

public interface MembershipSubscriptionService {
    MembershipSubscriptionResponse subscribe(Orders order);

    List<MembershipSubscriptionResponse> 
            getMemberSubscriptions(Long memberId);

    void updateStatus(Long id, SubscriptionStatus status);
}
