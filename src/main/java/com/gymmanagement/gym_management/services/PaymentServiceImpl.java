package com.gymmanagement.gym_management.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.PaymentRequest;
import com.gymmanagement.gym_management.entities.Order;
import com.gymmanagement.gym_management.entities.Payment;
import com.gymmanagement.gym_management.enums.OrderType;
import com.gymmanagement.gym_management.enums.PaymentStatus;
import com.gymmanagement.gym_management.repositories.OrderRepo;
import com.gymmanagement.gym_management.repositories.PaymentRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final OrderRepo orderRepo;
    private final PaymentRepo paymentRepo;
    private final CourseEnrollmentService courseEnrollmentService;
    private final ClassBookingService classBookingService;
    private final MembershipSubscriptionService membershipSubscriptionService;

    @Override
    public void processPayment(Long orderId, PaymentRequest req) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getPaymentStatus() == PaymentStatus.PAID) {
            throw new RuntimeException("Order already paid");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentMethod(req.getPaymentMethod());
        payment.setTransactionId(req.getTransactionId());
        payment.setPaymentStatus(PaymentStatus.PAID);
        payment.setPaymentDate(LocalDateTime.now());

        paymentRepo.save(payment);

        order.setPaymentStatus(PaymentStatus.PAID);
        orderRepo.save(order);

        if (order.getOrderType() == OrderType.COURSE) {
            courseEnrollmentService.enrollCourse(order);
        }

        if (order.getOrderType() == OrderType.CLASS) {
            classBookingService.book(order);
        }

        if (order.getOrderType() == OrderType.MEMBERSHIP) {
            membershipSubscriptionService.subscribe(order);
        }

        
    }

    @Override
    public List<Payment> getPaymentsByOrder(Long orderId) {
        return paymentRepo.findByOrderId(orderId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }
}
