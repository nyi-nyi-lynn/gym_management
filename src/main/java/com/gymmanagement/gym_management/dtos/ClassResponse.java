package com.gymmanagement.gym_management.dtos;

import java.math.BigDecimal;

import com.gymmanagement.gym_management.enums.ClassStatus;

import lombok.Data;

@Data
public class ClassResponse {
    private Long id;
    private String name;
    private String category;
    private String trainer;
    private BigDecimal price;
    private Integer capacity;
    private Integer duration;
    private ClassStatus status;
}
