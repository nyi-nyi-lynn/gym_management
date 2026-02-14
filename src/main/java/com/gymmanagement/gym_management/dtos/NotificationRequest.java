package com.gymmanagement.gym_management.dtos;

import com.gymmanagement.gym_management.enums.NotificationType;

import lombok.Data;

@Data
public class NotificationRequest {
private Long userId;
    private NotificationType type;
    private String message;
}
