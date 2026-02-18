package com.gymmanagement.gym_management.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MembershipPlanRequest {
    private String name;
    private BigDecimal price;
    private Integer durationMonths;
    private String description;
}
