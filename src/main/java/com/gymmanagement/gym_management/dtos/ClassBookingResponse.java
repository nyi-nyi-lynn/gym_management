package com.gymmanagement.gym_management.dtos;

import java.time.LocalDateTime;

import com.gymmanagement.gym_management.enums.BookingStatus;

import lombok.Data;

@Data
public class ClassBookingResponse {
    private Long id;
    private String className;
    private String memberName;
    private BookingStatus status;
    private LocalDateTime bookingDate;
}
