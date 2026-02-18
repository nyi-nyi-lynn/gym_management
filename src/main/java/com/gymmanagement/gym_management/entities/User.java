package com.gymmanagement.gym_management.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.gymmanagement.gym_management.enums.UserRole;
import com.gymmanagement.gym_management.enums.AccountStatus;
import com.gymmanagement.gym_management.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private UserRole role; // ADMIN, TRAINER, MEMBER

    @Enumerated(EnumType.STRING)
    private AccountStatus status; //ACTIVE , INACTIVE, SUSPENDED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // @OneToOne(mappedBy = "user")
    // private Member member;

    // @OneToOne(mappedBy = "user")
    // private Trainer trainer;

     @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
}
