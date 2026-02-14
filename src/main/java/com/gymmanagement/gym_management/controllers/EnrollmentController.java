package com.gymmanagement.gym_management.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.entities.Member;
import com.gymmanagement.gym_management.repositories.MemberRepo;
import com.gymmanagement.gym_management.security.CustomUserDetails;
import com.gymmanagement.gym_management.services.CourseEnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final CourseEnrollmentService enrollmentService;
    private final MemberRepo memberRepo;

    @GetMapping
    public ResponseEntity<?> getMyEnrollments(
            @AuthenticationPrincipal CustomUserDetails userDetails){

        Member member = memberRepo
                .findByUserId(userDetails.getId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return ResponseEntity.ok(
                enrollmentService.getMyEnrollments(member.getId())
        );
    }
}
