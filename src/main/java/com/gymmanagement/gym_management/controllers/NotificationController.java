package com.gymmanagement.gym_management.controllers;

import java.util.List;

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

    // ---------------- ADMIN ----------------
    @PostMapping("/admin")
    public NotificationResponse sendNotification(
            @RequestBody NotificationRequest req) {
        return service.sendNotification(req);
    }

    @PostMapping("/admin/broadcast")
    public void sendToAllUsers(
            @RequestBody NotificationRequest req) {
        service.sendToAllUsers(req);
    }

    // ---------------- USER ----------------
    @GetMapping("/user/{userId}")
    public List<NotificationResponse> getUserNotifications(
            @PathVariable Long userId) {
        return service.getUserNotifications(userId);
    }

    @PutMapping("/{notificationId}/read")
    public void markAsRead(@PathVariable Long notificationId) {
        service.markAsRead(notificationId);
    }
}
