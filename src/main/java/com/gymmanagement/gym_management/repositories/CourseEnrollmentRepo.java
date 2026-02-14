package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.CourseEnrollment;


@Repository
public interface CourseEnrollmentRepo extends JpaRepository<CourseEnrollment,Long> {
     List<CourseEnrollment> findByMemberId(Long memberId);
}
