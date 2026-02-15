package com.gymmanagement.gym_management.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class IncomeByTypeResponse {
    private String type;
    private BigDecimal totalIncome;
}
