package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.ClassResponse;
import com.gymmanagement.gym_management.security.CustomUserDetails;
import com.gymmanagement.gym_management.services.ClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trainer/classes")
@RequiredArgsConstructor
public class TrainerClassController {

     private final ClassService classService;

    // GET MY ASSIGNED CLASSES
    @GetMapping
    public ResponseEntity<List<ClassResponse>> getMyClasses(
            @AuthenticationPrincipal CustomUserDetails userDetails){

        return ResponseEntity.ok(
                classService.getTrainerClasses(userDetails.getId())
        );
    }
}
