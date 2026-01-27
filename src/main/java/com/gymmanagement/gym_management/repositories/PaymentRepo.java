package com.gymmanagement.gym_management.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.Payment;

@Repository
public interface PaymentRepo  extends JpaRepository<Payment,Long>{
    //Daily Income 
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.paymentDate = :date")
    Double getDailyIncome (LocalDate date);
    

    //Monthly Income
    @Query("""
            SELECT SUM(p.amount)
            FROM  Payment p
            WHERE MONTH(p.paymentDate) = :month
            AND YEAR(p.paymentDate) = :year
            """)
    Double getMonthlyIncome (int month,int year);

    @Query("SELECT SUM(p.amount) FROM Payment p")
    Double getTotalIncome();

    //all payments
    List<Payment> findAllByOrderByPaymentDateDesc();
}
