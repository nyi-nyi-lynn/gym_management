package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public MembershipPlanResponse create(
            @RequestBody MembershipPlanRequest req) {
        return service.create(req);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MembershipPlanResponse update(
            @PathVariable Long id,
            @RequestBody MembershipPlanRequest req) {
        return service.update(id, req);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER','MEMBER')")
    public List<MembershipPlanResponse> getAll() {
        return service.getAll();
    }
}
