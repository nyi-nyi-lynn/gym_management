package com.gymmanagement.gym_management.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.gymmanagement.gym_management.enums.AttendanceSource;
import com.gymmanagement.gym_management.enums.AttendanceStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "attendance")
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AttendanceSource source;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;
}
