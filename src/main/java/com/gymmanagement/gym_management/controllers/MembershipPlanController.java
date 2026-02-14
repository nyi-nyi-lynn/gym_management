package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gymmanagement.gym_management.dtos.MembershipPlanRequest;
import com.gymmanagement.gym_management.dtos.MembershipPlanResponse;
import com.gymmanagement.gym_management.services.MembershipPlanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/membership-plans")
@RequiredArgsConstructor
public class MembershipPlanController {

    private final MembershipPlanService service;

    // ADMIN - CREATE
    @PostMapping("/admin")
    public MembershipPlanResponse create(
            @RequestBody MembershipPlanRequest req) {
        return service.create(req);
    }


    // ADMIN - UPDATE
    @PutMapping("/admin/{id}")
    public MembershipPlanResponse update(
            @PathVariable Long id,
            @RequestBody MembershipPlanRequest req) {
        return service.update(id, req);
    }


    // ADMIN - DELETE
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ADMIN / MEMBER - GET ALL
    @GetMapping
    public List<MembershipPlanResponse> getAll() {
        return service.getAll();
    }
}
