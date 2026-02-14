package com.gymmanagement.gym_management.entities;

import java.math.BigDecimal;

import com.gymmanagement.gym_management.enums.PlanStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "membership_plan")
@Data
public class MembershipPlan {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;
    private Integer durationMonths;
    private String description;

    @Enumerated(EnumType.STRING)
    private PlanStatus status;
}
