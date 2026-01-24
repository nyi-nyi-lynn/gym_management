package com.gymmanagement.gym_management.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.repositories.PaymentRepo;

@Service
public class IncomeServiceImpl implements IncomeService{
    @Autowired
    PaymentRepo paymentRepo ; 

    @Override
    public double getTodayIncome() {
        Double total = paymentRepo.getDailyIncome(LocalDate.now());
        return total != null ? total : 0.0;
    }


    @Override
    public double getMonthlyIncome(int month , int year) {
        Double total = paymentRepo.getMonthlyIncome(month, year);
        return total != null ? total : 0.0;
    }
}
