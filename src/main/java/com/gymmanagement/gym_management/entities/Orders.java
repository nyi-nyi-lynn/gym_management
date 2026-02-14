package com.gymmanagement.gym_management.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import com.gymmanagement.gym_management.enums.OrderType;
import com.gymmanagement.gym_management.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name= "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

     @Enumerated(EnumType.STRING)
    private OrderType orderType;

    private Long referenceId;

    private BigDecimal totalAmount;

     @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate(){
        createdAt = LocalDateTime.now();
        paymentStatus = PaymentStatus.PENDING;
    }
}
