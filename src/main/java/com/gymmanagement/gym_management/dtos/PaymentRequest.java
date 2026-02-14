package com.gymmanagement.gym_management.dtos;

import com.gymmanagement.gym_management.enums.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {

     @NotNull
    private PaymentMethod paymentMethod;

    private String transactionId;
}
