package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/check-in")
    public AttendanceResponse checkIn(@RequestBody AttendanceRequest req){
        return service.checkIn(req);
    }

    @PutMapping("/check-out/{id}")
    public AttendanceResponse checkOut(@PathVariable Long id){
        return service.checkOut(id);
    }

    @GetMapping("/member/{memberId}")
    public List<AttendanceResponse> getMember(@PathVariable Long memberId){
        return service.getMemberAttendance(memberId);
    }

    @GetMapping("/admin")
    public List<AttendanceResponse> getAll(){
        return service.getAll();
    }

}
