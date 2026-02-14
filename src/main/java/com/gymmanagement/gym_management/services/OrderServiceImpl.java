package com.gymmanagement.gym_management.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.OrderCreateRequest;
import com.gymmanagement.gym_management.entities.ClassEntity;
import com.gymmanagement.gym_management.entities.Course;
import com.gymmanagement.gym_management.entities.MembershipPlan;
import com.gymmanagement.gym_management.entities.Orders;
import com.gymmanagement.gym_management.entities.User;
import com.gymmanagement.gym_management.enums.OrderType;
import com.gymmanagement.gym_management.enums.PaymentStatus;
import com.gymmanagement.gym_management.repositories.ClassRepo;
import com.gymmanagement.gym_management.repositories.CourseRepo;
import com.gymmanagement.gym_management.repositories.MembershipPlanRepo;
import com.gymmanagement.gym_management.repositories.OrdersRepo;
import com.gymmanagement.gym_management.repositories.UserRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrdersRepo ordersRepo;
    private final CourseRepo courseRepo;
    private final ClassRepo classRepo;
    private final MembershipPlanRepo membershipPlanRepo;
    private final UserRepo userRepo;

    @Override
    public Orders createOrder(OrderCreateRequest req, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Orders order = new Orders();
        order.setUser(user);
        order.setOrderType(req.getOrderType());
        order.setReferenceId(req.getReferenceId());
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        if (req.getOrderType() == OrderType.COURSE) {
            Course course = courseRepo.findById(req.getReferenceId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            order.setTotalAmount(course.getPrice());
        }

        if (req.getOrderType() == OrderType.CLASS) {
            ClassEntity clazz = classRepo.findById(req.getReferenceId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            order.setTotalAmount(clazz.getPrice());
        }

        if (req.getOrderType() == OrderType.MEMBERSHIP) {
            MembershipPlan plan = membershipPlanRepo.findById(req.getReferenceId())
                    .orElseThrow(() -> new RuntimeException("Plan not found"));
            order.setTotalAmount(plan.getPrice());
        }

        return ordersRepo.save(order);
    }

    @Override
    public List<Orders> getMyOrders(Long userId) {
        return ordersRepo.findByUserId(userId);
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepo.findAll();
    }

    @Override
    public Orders getById(Long id) {
        return ordersRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

}
