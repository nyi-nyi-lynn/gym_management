package com.gymmanagement.gym_management.entities;

import java.time.LocalDate;

import com.gymmanagement.gym_management.enums.MembershipStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate joinDate;
    private Double height;
    private Double weight;

    @Enumerated(EnumType.STRING)
    private MembershipStatus status;

    private boolean profileCompleted;

}
