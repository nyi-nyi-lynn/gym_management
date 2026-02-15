package com.gymmanagement.gym_management.services;

import java.time.LocalDate;
import java.util.List;

import com.gymmanagement.gym_management.dtos.IncomeByDateResponse;
import com.gymmanagement.gym_management.dtos.IncomeByTypeResponse;
import com.gymmanagement.gym_management.dtos.IncomeSummaryResponse;

public interface IncomeService {
    IncomeSummaryResponse getSummary(LocalDate from, LocalDate to);

    List<IncomeByTypeResponse> getIncomeByType(LocalDate from, LocalDate to);

    List<IncomeByDateResponse> getDailyIncome(LocalDate from, LocalDate to);

    List<IncomeByDateResponse> getMonthlyIncome(LocalDate from, LocalDate to);
}
