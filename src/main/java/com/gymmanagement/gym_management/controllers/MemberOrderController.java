package com.gymmanagement.gym_management.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.OrderCreateRequest;
import com.gymmanagement.gym_management.entities.Orders;
import com.gymmanagement.gym_management.security.CustomUserDetails;
import com.gymmanagement.gym_management.services.OrderService;
import com.gymmanagement.gym_management.services.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member/orders")
@RequiredArgsConstructor
public class MemberOrderController {
        private final OrderService orderService;
        private final PaymentService paymentService;

        @PostMapping
        public ResponseEntity<?> createOrder(
                        @RequestBody OrderCreateRequest req,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {

                return ResponseEntity.ok(
                                orderService.createOrder(req, userDetails.getId()));
        }

        @GetMapping
        public ResponseEntity<?> myOrders(
                        @AuthenticationPrincipal CustomUserDetails userDetails) {

                return ResponseEntity.ok(
                                orderService.getMyOrders(userDetails.getId()));
        }

        @GetMapping("/my/order/{orderId}")
        public ResponseEntity<?> getMyOrderPayments(
                        @PathVariable Long orderId,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {

                Orders order = orderService.getById(orderId);

                if (!order.getUser().getId().equals(userDetails.getId())) {
                        throw new RuntimeException("You are not allowed to view this payment");
                }

                return ResponseEntity.ok(
                                paymentService.getPaymentsByOrder(orderId));
        }
}
