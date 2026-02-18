package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.Notification;

@Repository
public interface NotificationRepo  extends JpaRepository<Notification,Long>{
      List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);
}
