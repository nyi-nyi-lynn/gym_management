package com.gymmanagement.gym_management.dtos;

import com.gymmanagement.gym_management.enums.AttendanceSource;

import lombok.Data;

@Data
public class AttendanceRequest {
    private Long memberId;
    private AttendanceSource source;
}