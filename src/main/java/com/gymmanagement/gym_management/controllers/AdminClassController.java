package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
import com.gymmanagement.gym_management.services.ClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/classes")
@RequiredArgsConstructor
public class AdminClassController {
    private final ClassService classService;

    // CREATE CLASS
    @PostMapping
    public ResponseEntity<ClassResponse> create(
            @RequestBody ClassRequest request){

        return ResponseEntity.ok(
                classService.create(request)
        );
    }

    // UPDATE CLASS
    @PutMapping("/{id}")
    public ResponseEntity<ClassResponse> update(
            @PathVariable Long id,
            @RequestBody ClassRequest request){

        return ResponseEntity.ok(
                classService.update(id, request)
        );
    }

    // SOFT DELETE (INACTIVE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id){

        classService.delete(id);
        return ResponseEntity.ok("Class deactivated successfully");
    }

    // GET ALL (ADMIN VIEW)
    @GetMapping
    public ResponseEntity<List<ClassResponse>> getAll(){
        return ResponseEntity.ok(
                classService.getAll()
        );
    }
}
