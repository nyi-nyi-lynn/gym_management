package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.ClassBookingResponse;
import com.gymmanagement.gym_management.security.CustomUserDetails;
import com.gymmanagement.gym_management.services.ClassBookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member/class-booking")
@RequiredArgsConstructor
public class MemberClassBookingController {
     private final ClassBookingService bookingService;

    

    // CANCEL BOOKING
    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancel(
            @PathVariable Long bookingId,
            @AuthenticationPrincipal CustomUserDetails userDetails){

        bookingService.cancel(bookingId, userDetails.getId());
        return ResponseEntity.ok("Booking cancelled successfully");
    }

    // GET MY BOOKINGS
    @GetMapping("/my")
    public ResponseEntity<List<ClassBookingResponse>> getMyBookings(
            @AuthenticationPrincipal CustomUserDetails userDetails){

        return ResponseEntity.ok(
                bookingService.getMyBookings(userDetails.getId())
        );
    }
}
