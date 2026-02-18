package com.gymmanagement.gym_management.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.Payment;

@Repository
public interface PaymentRepo  extends JpaRepository<Payment,Long>{
   List<Payment> findByOrderId(Long orderId);

}
