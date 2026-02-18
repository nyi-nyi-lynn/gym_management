package com.gymmanagement.gym_management.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message ="User Name is requried")
    private  String name ; 

    @Email(message = "Invalid Email Format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Phone is Required")
    private String phoneNumber;

    @NotBlank(message = "Address is Required")
    private String address;

    @NotBlank(message = "Gender is Required")
    private String gender;

    @NotNull(message = "Date of Birth is requied")
    private LocalDate dateOfBirth;

}
