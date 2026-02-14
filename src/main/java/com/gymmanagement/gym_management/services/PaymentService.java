package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.PaymentRequest;
import com.gymmanagement.gym_management.entities.Payment;

public interface PaymentService {
    void processPayment(Long orderId, PaymentRequest request);

    List<Payment> getPaymentsByOrder(Long orderId);

    List<Payment> getAllPayments();
}
