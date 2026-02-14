package com.gymmanagement.gym_management.entities;

import java.time.LocalDateTime;

import com.gymmanagement.gym_management.enums.CategoryStatus;

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
@Table(name ="category")
@Data
public  class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;

        @Column(nullable = false, unique = true)
        private String name; 


        private String description;

        @Enumerated(EnumType.STRING)
        private CategoryStatus status;

        private LocalDateTime  createdAt;

        private LocalDateTime updatedAt;
}
