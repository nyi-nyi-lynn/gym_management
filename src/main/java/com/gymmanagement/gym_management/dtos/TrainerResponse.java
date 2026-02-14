package com.gymmanagement.gym_management.dtos;

import java.time.LocalDate;

import com.gymmanagement.gym_management.enums.EmploymentStatus;

import lombok.Data;

@Data
public class TrainerResponse {
    private Long trainerId;
    private Long userId;

    private String name;
    private String email;

    private String specialization;
    private Integer experienceYears;
    private String bio;
    private LocalDate startWorkDate;

    private EmploymentStatus status;
}
