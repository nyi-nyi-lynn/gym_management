package com.gymmanagement.gym_management.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class IncomeSummaryResponse {
    private LocalDate from;
    private LocalDate to;
    private BigDecimal totalIncome;
    private Integer paidCount;
}
