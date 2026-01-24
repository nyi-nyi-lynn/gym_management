package com.gymmanagement.gym_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.MembershipPlan;

@Repository
public interface MembershipPlanRepo extends JpaRepository<MembershipPlan,Long>{

}
