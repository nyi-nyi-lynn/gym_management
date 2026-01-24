package com.gymmanagement.gym_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.services.IncomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/income")
public class IncomeController {
    @Autowired
    IncomeService service;

    @GetMapping("/today")
    public Double  getTodyIncome() {
        return service.getTodayIncome();
    }

    @GetMapping("/monthly")
    public Double getMonthlyIncome(@RequestParam int month , @RequestParam int year) {
        return service.getMonthlyIncome(month, year);
    }
    
    
}
