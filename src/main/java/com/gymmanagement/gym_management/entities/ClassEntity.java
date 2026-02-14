package com.gymmanagement.gym_management.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gymmanagement.gym_management.enums.ClassStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "classes")
@Data
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name; 

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    private BigDecimal price;

    private Integer capacity; 

    private Integer duration;

    @Enumerated(EnumType.STRING)
    private ClassStatus status;

    private LocalDateTime createdAt;
}
