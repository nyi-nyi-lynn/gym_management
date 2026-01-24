package com.gymmanagement.gym_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.PaymentRequestDTO;
import com.gymmanagement.gym_management.services.PaymentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @PostMapping
    public String makePayment(@Valid @RequestBody PaymentRequestDTO dto) {        
        service.makePayment(dto);
        return "Payment Successful";
    }
    
}
