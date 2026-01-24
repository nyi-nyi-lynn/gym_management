package com.gymmanagement.gym_management.services;

public interface IncomeService {
    double getTodayIncome();

    double getMonthlyIncome(int month, int year);
}
