package com.gymmanagement.gym_management.services;

import com.gymmanagement.gym_management.dtos.LoginRequestDTO;

public interface AuthService {
    String login (LoginRequestDTO dto);
}
