package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.ClassBookingResponse;
import com.gymmanagement.gym_management.services.ClassBookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/class-bookings")
@RequiredArgsConstructor
public class AdminClassBookingController {
     private final ClassBookingService bookingService;

    // VIEW BOOKINGS BY CLASS
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<ClassBookingResponse>> getBookingsByClass(
            @PathVariable Long classId){

        return ResponseEntity.ok(
                bookingService.getBookingsByClass(classId)
        );
    }
}
