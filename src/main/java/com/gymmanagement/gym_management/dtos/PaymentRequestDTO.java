package com.gymmanagement.gym_management.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequestDTO {
    @NotNull(message = "Member Id required")
    private Long memberId;

    @NotNull(message = "Subscription Id required")
    private Long subscriptionId;

    @Positive(message = "Amount cam't be Negative value")
    private Double amount ;

    @NotNull(message = "Payment method is required")
    private String paymentMethod;


}
