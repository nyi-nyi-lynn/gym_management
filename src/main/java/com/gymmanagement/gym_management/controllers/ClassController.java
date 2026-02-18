package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.ClassRequest;
import com.gymmanagement.gym_management.dtos.ClassResponse;
import com.gymmanagement.gym_management.security.CustomUserDetails;
import com.gymmanagement.gym_management.services.ClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER','MEMBER')")
    public ResponseEntity<List<ClassResponse>> browseActive() {
        return ResponseEntity.ok(classService.browseActive());
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<List<ClassResponse>> getMyClasses(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(classService.getTrainerClasses(userDetails.getId()));
    }

    @GetMapping("/manage")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClassResponse>> getAll() {
        return ResponseEntity.ok(classService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public ResponseEntity<ClassResponse> create(@RequestBody ClassRequest request) {
        return ResponseEntity.ok(classService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public ResponseEntity<ClassResponse> update(
            @PathVariable Long id,
            @RequestBody ClassRequest request) {
        return ResponseEntity.ok(classService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        classService.delete(id);
        return ResponseEntity.ok("Class deactivated successfully");
    }
}
