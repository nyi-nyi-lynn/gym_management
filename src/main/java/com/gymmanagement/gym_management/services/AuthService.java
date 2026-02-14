package com.gymmanagement.gym_management.services;

import com.gymmanagement.gym_management.dtos.LoginRequestDTO;
import com.gymmanagement.gym_management.dtos.RegisterRequest;

public interface AuthService {
    String login (LoginRequestDTO dto);

    void registerMember(RegisterRequest request);

    void registerTrainer(RegisterRequest request);
}
