package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.UserRequestDTO;
import com.gymmanagement.gym_management.dtos.UserResponseDTO;
import com.gymmanagement.gym_management.entities.User;


public interface UserService {

    UserResponseDTO createUser(UserRequestDTO user);

    List<User> getAllUsers();

    User getUserById(Long id);

}
