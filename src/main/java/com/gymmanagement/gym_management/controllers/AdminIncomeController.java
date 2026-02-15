package com.gymmanagement.gym_management.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.IncomeByDateResponse;
import com.gymmanagement.gym_management.dtos.IncomeByTypeResponse;
import com.gymmanagement.gym_management.dtos.IncomeSummaryResponse;
import com.gymmanagement.gym_management.services.IncomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/income/manage")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminIncomeController {

    private final IncomeService incomeService;

    @GetMapping("/summary")
    public IncomeSummaryResponse getSummary(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return incomeService.getSummary(from, to);
    }

    @GetMapping("/by-type")
    public List<IncomeByTypeResponse> getByType(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return incomeService.getIncomeByType(from, to);
    }

    @GetMapping("/daily")
    public List<IncomeByDateResponse> getDaily(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return incomeService.getDailyIncome(from, to);
    }

    @GetMapping("/monthly")
    public List<IncomeByDateResponse> getMonthly(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return incomeService.getMonthlyIncome(from, to);
    }
}
