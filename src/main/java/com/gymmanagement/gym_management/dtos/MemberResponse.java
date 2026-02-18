package com.gymmanagement.gym_management.dtos;

import java.time.LocalDate;

import com.gymmanagement.gym_management.enums.Gender;
import com.gymmanagement.gym_management.enums.MembershipStatus;

import lombok.Data;

@Data
public class MemberResponse {
    private Long id;
    private Long userId;

    private String name;
    private String email;

    private Double height;
    private Double weight;
    private Gender gender;
    private Integer age;

    private LocalDate joinDate;
    private MembershipStatus status;
}
