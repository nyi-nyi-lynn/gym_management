package com.gymmanagement.gym_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.LoginRequestDTO;
import com.gymmanagement.gym_management.dtos.RegisterRequest;
import com.gymmanagement.gym_management.services.AuthService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO dto) {
       return service.login(dto);
    }

    @PostMapping("/register/member")
    public String registerMember(@Valid @RequestBody RegisterRequest request) {
        service.registerMember(request);
        return "Member register Successfully";
    }

    @PostMapping("/register/trainer")
    public String registerTrainer(@RequestBody RegisterRequest request) {
        service.registerTrainer(request);
        return "Trainer register successfully";
    }
    
    
    
}
