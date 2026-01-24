package com.gymmanagement.gym_management.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.PaymentRequestDTO;
import com.gymmanagement.gym_management.entities.MemberSubscription;
import com.gymmanagement.gym_management.entities.Payment;
import com.gymmanagement.gym_management.entities.User;
import com.gymmanagement.gym_management.repositories.MemberSubscriptionRepo;
import com.gymmanagement.gym_management.repositories.PaymentRepo;
import com.gymmanagement.gym_management.repositories.UserRepo;

@Service
public class PaymentServiceImpl  implements PaymentService{

    @Autowired
    UserRepo userRepo;

    @Autowired 
    MemberSubscriptionRepo subscriptionRepo;

    @Autowired
    PaymentRepo paymentRepo;

    @Override
    public void makePayment(PaymentRequestDTO dto) {
        User member = userRepo.findById(dto.getMemberId())
        .orElseThrow(()-> new RuntimeException("Member not Found"));

        MemberSubscription subscription = subscriptionRepo.findById(dto.getSubscriptionId())
        .orElseThrow(()-> new RuntimeException("Subscription not Found"));

        Payment payment = new Payment();
        payment.setMember(member);
        payment.setSubscription(subscription);
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod(dto.getPaymentMethod());
    
        paymentRepo.save(payment);

    }
}
