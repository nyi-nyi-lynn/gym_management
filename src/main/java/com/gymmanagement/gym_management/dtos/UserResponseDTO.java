package com.gymmanagement.gym_management.dtos;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String role;
    private boolean active;
}
