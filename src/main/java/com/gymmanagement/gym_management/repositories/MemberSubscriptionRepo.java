package com.gymmanagement.gym_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.MemberSubscription;

@Repository
public interface MemberSubscriptionRepo extends JpaRepository<MemberSubscription,Long> {

}
