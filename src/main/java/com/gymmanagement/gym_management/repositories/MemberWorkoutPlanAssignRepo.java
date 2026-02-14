package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.Member;
import com.gymmanagement.gym_management.entities.MemberWorkoutPlanAssign;

@Repository
public interface MemberWorkoutPlanAssignRepo extends JpaRepository<MemberWorkoutPlanAssign, Long> {
    List<MemberWorkoutPlanAssign> findByMember(Member member);
}
