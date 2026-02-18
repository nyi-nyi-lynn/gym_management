package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.OrderCreateRequest;
import com.gymmanagement.gym_management.entities.Order;

public interface OrderService {
    Order createOrder(OrderCreateRequest request, Long userId);

    List<Order> getMyOrders(Long userId);

    List<Order> getAllOrders();

    Order getById(Long id);
}
