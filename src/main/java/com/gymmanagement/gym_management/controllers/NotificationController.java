package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gymmanagement.gym_management.dtos.NotificationRequest;
import com.gymmanagement.gym_management.dtos.NotificationResponse;
import com.gymmanagement.gym_management.services.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public NotificationResponse sendNotification(
            @RequestBody NotificationRequest req) {
        return service.sendNotification(req);
    }

    @PostMapping("/broadcast")
    @PreAuthorize("hasRole('ADMIN')")
    public void sendToAllUsers(
            @RequestBody NotificationRequest req) {
        service.sendToAllUsers(req);
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER','MEMBER')")
    public List<NotificationResponse> getUserNotifications(
            @PathVariable Long userId) {
        return service.getUserNotifications(userId);
    }

    @PatchMapping("/{notificationId}/read")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER','MEMBER')")
    public void markAsRead(@PathVariable Long notificationId) {
        service.markAsRead(notificationId);
    }
}
