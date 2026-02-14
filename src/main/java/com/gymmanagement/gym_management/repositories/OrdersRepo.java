package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.Orders;

@Repository
public interface OrdersRepo  extends JpaRepository<Orders,Long>{
        List<Orders> findByUserId(Long userId);

}
