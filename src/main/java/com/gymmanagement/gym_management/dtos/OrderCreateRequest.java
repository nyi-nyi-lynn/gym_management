package com.gymmanagement.gym_management.dtos;

import com.gymmanagement.gym_management.enums.OrderType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderCreateRequest {

     @NotNull
    private OrderType orderType;

    @NotNull
    private Long referenceId;
}
