package com.gymmanagement.gym_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.LoginRequestDTO;
import com.gymmanagement.gym_management.entities.User;
import com.gymmanagement.gym_management.repositories.UserRepo;
import com.gymmanagement.gym_management.security.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(LoginRequestDTO dto) {
       User user = userRepo.findByEmail(dto.getEmail())
       .orElseThrow(()-> new RuntimeException("Invalid email"));

        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

       return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }
}
