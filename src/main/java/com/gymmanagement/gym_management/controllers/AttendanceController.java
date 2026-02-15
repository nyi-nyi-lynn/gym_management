package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.AttendanceRequest;
import com.gymmanagement.gym_management.dtos.AttendanceResponse;
import com.gymmanagement.gym_management.services.AttendanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService service;

    @PostMapping("/check-ins")
    @PreAuthorize("hasRole('MEMBER')")
    public AttendanceResponse checkIn(@RequestBody AttendanceRequest req){
        return service.checkIn(req);
    }

    @PatchMapping("/{id}/check-out")
    @PreAuthorize("hasRole('MEMBER')")
    public AttendanceResponse checkOut(@PathVariable Long id){
        return service.checkOut(id);
    }

    @GetMapping("/members/{memberId}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER','MEMBER')")
    public List<AttendanceResponse> getMember(@PathVariable Long memberId){
        return service.getMemberAttendance(memberId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AttendanceResponse> getAll(){
        return service.getAll();
    }

}
