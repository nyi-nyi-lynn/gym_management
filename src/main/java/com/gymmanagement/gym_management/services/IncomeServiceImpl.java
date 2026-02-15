package com.gymmanagement.gym_management.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.IncomeByDateResponse;
import com.gymmanagement.gym_management.dtos.IncomeByTypeResponse;
import com.gymmanagement.gym_management.dtos.IncomeSummaryResponse;
import com.gymmanagement.gym_management.entities.Payment;
import com.gymmanagement.gym_management.enums.PaymentStatus;
import com.gymmanagement.gym_management.repositories.PaymentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final PaymentRepo paymentRepo;

    @Override
    public IncomeSummaryResponse getSummary(LocalDate from, LocalDate to) {
        List<Payment> payments = getPaidPaymentsInRange(from, to);
        BigDecimal total = payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        IncomeSummaryResponse response = new IncomeSummaryResponse();
        response.setFrom(from);
        response.setTo(to);
        response.setTotalIncome(total);
        response.setPaidCount(payments.size());
        return response;
    }

    @Override
    public List<IncomeByTypeResponse> getIncomeByType(LocalDate from, LocalDate to) {
        List<Payment> payments = getPaidPaymentsInRange(from, to);

        Map<String, BigDecimal> grouped = payments.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getOrder().getOrderType().name(),
                        Collectors.mapping(Payment::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        return grouped.entrySet().stream()
                .map(e -> {
                    IncomeByTypeResponse r = new IncomeByTypeResponse();
                    r.setType(e.getKey());
                    r.setTotalIncome(e.getValue());
                    return r;
                })
                .sorted(Comparator.comparing(IncomeByTypeResponse::getType))
                .toList();
    }

    @Override
    public List<IncomeByDateResponse> getDailyIncome(LocalDate from, LocalDate to) {
        List<Payment> payments = getPaidPaymentsInRange(from, to);

        Map<LocalDate, BigDecimal> grouped = payments.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getPaymentDate().toLocalDate(),
                        Collectors.mapping(Payment::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        return grouped.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new IncomeByDateResponse(e.getKey().toString(), e.getValue()))
                .toList();
    }

    @Override
    public List<IncomeByDateResponse> getMonthlyIncome(LocalDate from, LocalDate to) {
        List<Payment> payments = getPaidPaymentsInRange(from, to);

        Map<YearMonth, BigDecimal> grouped = payments.stream()
                .collect(Collectors.groupingBy(
                        p -> YearMonth.from(p.getPaymentDate()),
                        Collectors.mapping(Payment::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        return grouped.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new IncomeByDateResponse(e.getKey().toString(), e.getValue()))
                .toList();
    }

    private List<Payment> getPaidPaymentsInRange(LocalDate from, LocalDate to) {
        return paymentRepo.findAll().stream()
                .filter(p -> p.getPaymentStatus() == PaymentStatus.PAID)
                .filter(p -> p.getPaymentDate() != null)
                .filter(p -> from == null || !p.getPaymentDate().toLocalDate().isBefore(from))
                .filter(p -> to == null || !p.getPaymentDate().toLocalDate().isAfter(to))
                .toList();
    }
}
