package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.ClassBooking;
import com.gymmanagement.gym_management.enums.BookingStatus;

@Repository
public interface ClassBookingRepo extends JpaRepository<ClassBooking, Long> {
    long countByClassEntityId(Long classId);

    List<ClassBooking> findByMemberId(Long memberId);

    List<ClassBooking> findByClassEntityId(Long classId);

    boolean existsByMemberIdAndClassEntityIdAndStatus(
            Long memberId,
            Long classId,
            BookingStatus status);
}
