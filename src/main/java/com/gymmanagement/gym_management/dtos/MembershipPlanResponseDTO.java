package com.gymmanagement.gym_management.dtos;

import lombok.Data;

@Data
public class MembershipPlanResponseDTO {
    private Long id;
    private String plan_name;
    private Double price;
    private int durationMonths;
    private boolean active;
}
