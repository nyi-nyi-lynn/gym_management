package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.NotificationRequest;
import com.gymmanagement.gym_management.dtos.NotificationResponse;

public interface NotificationService {
    NotificationResponse sendNotification(NotificationRequest req);

    void sendToAllUsers(NotificationRequest req);

    List<NotificationResponse> getUserNotifications(Long userId);

    void markAsRead(Long notificationId);
}
