package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.ClassBookingResponse;
import com.gymmanagement.gym_management.security.CustomUserDetails;
import com.gymmanagement.gym_management.services.ClassBookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/class-bookings")
@RequiredArgsConstructor
public class ClassBookingController {
    private final ClassBookingService bookingService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<List<ClassBookingResponse>> getMyBookings(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(bookingService.getMyBookings(userDetails.getId()));
    }

    @PatchMapping("/{bookingId}/status/cancel")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<String> cancel(
            @PathVariable Long bookingId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        bookingService.cancel(bookingId, userDetails.getId());
        return ResponseEntity.ok("Booking cancelled successfully");
    }

    @GetMapping("/classes/{classId}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public ResponseEntity<List<ClassBookingResponse>> getBookingsByClass(
            @PathVariable Long classId) {
        return ResponseEntity.ok(bookingService.getBookingsByClass(classId));
    }

    @PatchMapping("/{bookingId}/attendance")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public ResponseEntity<String> markAttendance(@PathVariable Long bookingId) {
        bookingService.markAttendance(bookingId);
        return ResponseEntity.ok("Attendance marked successfully");
    }

    @GetMapping("/manage/classes/{classId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClassBookingResponse>> getBookingsByClassManage(
            @PathVariable Long classId) {
        return ResponseEntity.ok(bookingService.getBookingsByClass(classId));
    }
}
