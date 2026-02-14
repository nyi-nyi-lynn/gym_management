package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.OrderCreateRequest;
import com.gymmanagement.gym_management.entities.Orders;

public interface OrderService {
    Orders createOrder(OrderCreateRequest request, Long userId);

    List<Orders> getMyOrders(Long userId);

    List<Orders> getAllOrders();

    Orders getById(Long id);
}
