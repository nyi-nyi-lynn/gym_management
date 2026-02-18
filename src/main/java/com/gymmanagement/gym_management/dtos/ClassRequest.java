package com.gymmanagement.gym_management.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ClassRequest {
     private String name;
    private Long categoryId;
    private Long trainerId;
    private BigDecimal price;
    private Integer capacity;
    private Integer duration;
}
