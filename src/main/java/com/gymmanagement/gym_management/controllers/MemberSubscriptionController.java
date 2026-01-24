package com.gymmanagement.gym_management.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.SubscribeRequestDTO;
import com.gymmanagement.gym_management.entities.MemberSubscription;
import com.gymmanagement.gym_management.services.MemberSubscriptionService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/subscriptions")
public class MemberSubscriptionController {

    @Autowired
    private MemberSubscriptionService service;

    @PostMapping
    public String subscribe(@Valid @RequestBody SubscribeRequestDTO dto) {

       service.subscribe(dto);
       return "Member Subscribe successfully";
    }
    
}
