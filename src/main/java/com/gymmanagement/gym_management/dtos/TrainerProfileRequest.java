package com.gymmanagement.gym_management.dtos;
import lombok.Data;

@Data
public class TrainerProfileRequest {
    private String specialization;
    private Integer experienceYears;
    private String bio;
}
