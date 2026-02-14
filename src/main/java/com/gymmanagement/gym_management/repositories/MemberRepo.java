package com.gymmanagement.gym_management.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member,Long> {
    Optional<Member> findByUserId (long userId);
}
