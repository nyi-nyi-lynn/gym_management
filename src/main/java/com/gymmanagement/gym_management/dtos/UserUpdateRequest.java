package com.gymmanagement.gym_management.dtos;

import java.time.LocalDate;

import com.gymmanagement.gym_management.enums.UserRole;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String name; 

    private String phoneNumber;

    private String address;

    private String gender;

    private LocalDate dateOfBirth;

    private UserRole role; // AMIN , TRAINER , MEMBER
}
