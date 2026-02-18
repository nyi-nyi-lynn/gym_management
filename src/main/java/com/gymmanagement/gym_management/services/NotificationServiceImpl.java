package com.gymmanagement.gym_management.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.NotificationRequest;
import com.gymmanagement.gym_management.dtos.NotificationResponse;
import com.gymmanagement.gym_management.entities.Notification;
import com.gymmanagement.gym_management.entities.User;
import com.gymmanagement.gym_management.enums.NotificationStatus;
import com.gymmanagement.gym_management.repositories.NotificationRepo;
import com.gymmanagement.gym_management.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepo repo;
    private final UserRepo userRepo;

    @Override
    public NotificationResponse sendNotification(NotificationRequest req) {

        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setType(req.getType());
        notification.setMessage(req.getMessage());
        notification.setStatus(NotificationStatus.UNREAD);
        notification.setCreatedAt(LocalDateTime.now());

        repo.save(notification);

        return map(notification);
    }

    @Override
    public List<NotificationResponse> getUserNotifications(Long userId) {

        return repo.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public void markAsRead(Long notificationId) {

        Notification notification = repo.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setStatus(NotificationStatus.READ);
        repo.save(notification);
    }

    @Override
    public void sendToAllUsers(NotificationRequest req) {
        List<User> users = userRepo.findAll();

        List<Notification> notifications = users.stream()
                .map(user -> {
                    Notification n = new Notification();
                    n.setUser(user);
                    n.setType(req.getType());
                    n.setMessage(req.getMessage());
                    n.setStatus(NotificationStatus.UNREAD);
                    n.setCreatedAt(LocalDateTime.now());
                    return n;
                })
                .toList();

        repo.saveAll(notifications);
    }

    // Helper Methods

    private NotificationResponse map(Notification n) {

        NotificationResponse res = new NotificationResponse();
        res.setId(n.getId());
        res.setUserId(n.getUser().getId());
        res.setType(n.getType());
        res.setMessage(n.getMessage());
        res.setStatus(n.getStatus());
        res.setCreatedAt(n.getCreatedAt());

        return res;
    }
}
