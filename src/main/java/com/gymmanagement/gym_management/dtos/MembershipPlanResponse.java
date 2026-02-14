package com.gymmanagement.gym_management.dtos;

import java.math.BigDecimal;

import com.gymmanagement.gym_management.enums.PlanStatus;

import lombok.Data;

@Data
public class MembershipPlanResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer durationMonths;
    private String description;
    private PlanStatus status;
}
