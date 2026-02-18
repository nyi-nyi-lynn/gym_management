package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.UserCreateRequest;
import com.gymmanagement.gym_management.dtos.UserResponse;
import com.gymmanagement.gym_management.dtos.UserUpdateRequest;
import com.gymmanagement.gym_management.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse createUser(@Valid @RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }


    @PutMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public String activateUser(@PathVariable Long id) {
        userService.activateUser(id);
        return "User activated";
    }

    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public String deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return "User deactivated";
    }

    @PutMapping("/{id}/suspended")
    @PreAuthorize("hasRole('ADMIN')")
    public String banUser(@PathVariable Long id) {
        userService.banUser(id);
        return "User suspended";
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUserStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        String value = status.toLowerCase();
        if ("active".equals(value)) {
            userService.activateUser(id);
            return "User activated";
        }
        if ("inactive".equals(value) || "deactive".equals(value) || "deactivated".equals(value)) {
            userService.deactivateUser(id);
            return "User deactivated";
        }
        if ("suspended".equals(value)) {
            userService.banUser(id);
            return "User suspended";
        }
        throw new RuntimeException("Unsupported status: " + status);
    }

    

}
