package com.gymmanagement.gym_management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.UserRequestDTO;
import com.gymmanagement.gym_management.dtos.UserResponseDTO;
import com.gymmanagement.gym_management.entities.User;
import com.gymmanagement.gym_management.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
       
        if(userRepo.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("Email already in use");
        }
         User user = new User();
         user.setName(dto.getName());
         user.setEmail(dto.getEmail());
         user.setPassword(passwordEncoder.encode(dto.getPassword()));
         user.setRole(dto.getRole());

         User savedUser = userRepo.save(user);

         UserResponseDTO responseDTO = new UserResponseDTO();
         responseDTO.setId(savedUser.getId());
         responseDTO.setName(savedUser.getName());
            responseDTO.setEmail(savedUser.getEmail());
            responseDTO.setRole(savedUser.getRole());   
            responseDTO.setActive(savedUser.isActive());
      return responseDTO;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}
