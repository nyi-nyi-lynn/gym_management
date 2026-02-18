package com.gymmanagement.gym_management.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.OrderCreateRequest;
import com.gymmanagement.gym_management.entities.Order;
import com.gymmanagement.gym_management.security.CustomUserDetails;
import com.gymmanagement.gym_management.services.OrderService;
import com.gymmanagement.gym_management.services.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final PaymentService paymentService;

    @PostMapping
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> createOrder(
            @RequestBody OrderCreateRequest req,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(orderService.createOrder(req, userDetails.getId()));
    }

    @GetMapping
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> myOrders(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(orderService.getMyOrders(userDetails.getId()));
    }

    @GetMapping("/{orderId}/payments")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> getMyOrderPayments(
            @PathVariable Long orderId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Order order = orderService.getById(orderId);
        if (!order.getUser().getId().equals(userDetails.getId())) {
            throw new RuntimeException("You are not allowed to view this payment");
        }
        return ResponseEntity.ok(paymentService.getPaymentsByOrder(orderId));
    }

    @GetMapping("/manage")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/manage/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping("/manage/payments")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/manage/{orderId}/payments")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPaymentsByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.getPaymentsByOrder(orderId));
    }
}
