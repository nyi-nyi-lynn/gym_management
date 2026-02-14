package com.gymmanagement.gym_management.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.gymmanagement.gym_management.enums.AttendanceStatus;

import lombok.Data;


@Data
public class AttendanceResponse {
     private Long id;
    private Long memberId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private LocalDate date;
    private AttendanceStatus status;
}
