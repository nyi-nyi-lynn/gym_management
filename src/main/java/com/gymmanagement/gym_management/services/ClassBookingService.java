package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.ClassBookingResponse;
import com.gymmanagement.gym_management.entities.Orders;

public interface ClassBookingService {
     ClassBookingResponse book(Orders order);

    void cancel(Long bookingId, Long memberId);

    void markAttendance(Long bookingId);

    List<ClassBookingResponse> getMyBookings(Long memberId);

    List<ClassBookingResponse> getBookingsByClass(Long classId);
}
