package com.gymmanagement.gym_management.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class SubscribeRequestDTO{
    @NotNull(message = "Member Id is required")
    private Long memberId;

    @NotNull(message = "Plan Id is required")
    private Long planId;


}
