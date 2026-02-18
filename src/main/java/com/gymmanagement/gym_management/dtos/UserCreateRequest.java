package com.gymmanagement.gym_management.dtos;

import java.time.LocalDate;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateRequest {
    
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String phoneNumber;
    private String address;

    private String gender;

    private LocalDate dateOfBirth;

    @NotBlank(message = "Role is required")
    private String role;
}
