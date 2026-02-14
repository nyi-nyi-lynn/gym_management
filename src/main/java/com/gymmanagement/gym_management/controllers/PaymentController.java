package com.gymmanagement.gym_management.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.PaymentRequest;
import com.gymmanagement.gym_management.services.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentService paymentService;

    @PostMapping("/{orderId}")
    public ResponseEntity<?> pay(
            @PathVariable Long orderId,
            @RequestBody PaymentRequest req){

        paymentService.processPayment(orderId, req);
        return ResponseEntity.ok("Payment Successful");
    }

    
    
}
