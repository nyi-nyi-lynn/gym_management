package com.gymmanagement.gym_management.dtos;

import java.time.LocalDateTime;

import com.gymmanagement.gym_management.enums.NotificationStatus;
import com.gymmanagement.gym_management.enums.NotificationType;

import lombok.Data;

@Data
public class NotificationResponse {
     private Long id;
    private Long userId;
    private NotificationType type;
    private String message;
    private NotificationStatus status;
    private LocalDateTime createdAt;
}
