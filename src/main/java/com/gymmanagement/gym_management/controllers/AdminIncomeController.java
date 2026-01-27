package com.gymmanagement.gym_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.services.IncomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/admin/income")
public class AdminIncomeController {
    @Autowired
    IncomeService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/today")
    public Double  getTodyIncome() {
        return service.getTodayIncome();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/monthly")
    public Double getMonthlyIncome(@RequestParam int month , @RequestParam int year) {
        return service.getMonthlyIncome(month, year);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/total")
    public Double getTotalIncome() {
        return service.getTotalIncome();
    }
    
    
    
}
