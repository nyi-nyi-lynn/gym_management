package com.gymmanagement.gym_management.entities;

import java.time.LocalDate;
import java.util.concurrent.Flow.Subscription;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User member;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private MemberSubscription subscription;

    private Double amount ;

    private LocalDate paymentDate;

    private String paymentMethod;
}
