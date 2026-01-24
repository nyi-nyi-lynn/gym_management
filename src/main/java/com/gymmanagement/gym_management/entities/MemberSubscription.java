package com.gymmanagement.gym_management.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "member_subscriptions")
public class MemberSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User member;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private MembershipPlan plan;


    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active = true;
}
