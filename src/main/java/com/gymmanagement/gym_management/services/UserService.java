package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.UserCreateRequest;
import com.gymmanagement.gym_management.dtos.UserResponse;
import com.gymmanagement.gym_management.dtos.UserUpdateRequest;


public interface UserService {

    UserResponse createUser(UserCreateRequest user);

    UserResponse updateUser(Long id, UserUpdateRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    void deleteUser(Long id);

    void activateUser(Long id);

    void deactivateUser(Long id);

    void banUser(Long id);
}
