package com.gymmanagement.gym_management.entities;

import java.time.LocalDate;

import com.gymmanagement.gym_management.enums.EmploymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "trainers")
@Data
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private String specialization;
    private Integer experienceYears;

    @Column(length = 1000)
    private String bio;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus status;

    private LocalDate startWorkDate;

    private boolean profileCompleted;
}
