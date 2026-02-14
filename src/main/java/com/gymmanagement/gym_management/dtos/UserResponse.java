package com.gymmanagement.gym_management.dtos;

import com.gymmanagement.gym_management.enums.UserRole;

import java.time.LocalDate;

import com.gymmanagement.gym_management.enums.AccountStatus;
import com.gymmanagement.gym_management.enums.Gender;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Gender gender;
    private LocalDate dateOfBirth;
    private UserRole role;
    private AccountStatus status;
}
