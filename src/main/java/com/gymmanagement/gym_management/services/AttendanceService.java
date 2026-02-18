package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.AttendanceRequest;
import com.gymmanagement.gym_management.dtos.AttendanceResponse;

public interface AttendanceService {
    AttendanceResponse checkIn(AttendanceRequest req);

    AttendanceResponse checkOut(Long attendanceId);

    List<AttendanceResponse> getMemberAttendance(Long memberId);

    List<AttendanceResponse> getAll();
}
