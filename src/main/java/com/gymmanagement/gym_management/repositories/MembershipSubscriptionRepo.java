package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.MembershipSubscription;

@Repository
public interface MembershipSubscriptionRepo extends JpaRepository<MembershipSubscription,Long> {
    List<MembershipSubscription> findByMemberId(Long id);
}
