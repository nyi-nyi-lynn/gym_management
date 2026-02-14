package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.ClassResponse;
import com.gymmanagement.gym_management.services.ClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member/classes")
@RequiredArgsConstructor
public class MemberClassController {
     private final ClassService classService;

    // BROWSE ACTIVE CLASSES
    @GetMapping
    public ResponseEntity<List<ClassResponse>> browseActive(){
        return ResponseEntity.ok(
                classService.browseActive()
        );
    }
}
