package com.gymmanagement.gym_management.services;

import com.gymmanagement.gym_management.dtos.PaymentRequestDTO;

public interface PaymentService {
    void makePayment(PaymentRequestDTO dto);
}
