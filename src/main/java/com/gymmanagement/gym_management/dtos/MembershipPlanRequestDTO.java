package com.gymmanagement.gym_management.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MembershipPlanRequestDTO {
    @NotBlank(message = "Plan name is required")
    private String plan_name;

    @Positive(message = "price must be greater than zero")
    private Double price;

    @Positive(message = "Duration must be greater than zero")
    private int durationMonths;

}
