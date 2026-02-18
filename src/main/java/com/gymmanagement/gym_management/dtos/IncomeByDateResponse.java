package com.gymmanagement.gym_management.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeByDateResponse {
    private String period;
    private BigDecimal totalIncome;
}
