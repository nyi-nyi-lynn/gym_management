package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.Attendance;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Long>{
    List<Attendance>  findByMemberId(Long memberId);
}
