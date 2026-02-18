package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gymmanagement.gym_management.dtos.MemberWorkoutAssignRequest;
import com.gymmanagement.gym_management.dtos.MemberWorkoutAssignResponse;
import com.gymmanagement.gym_management.services.MemberWorkoutAssignService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workout-assignments")
@RequiredArgsConstructor
public class MemberWorkoutAssignController {

    private final MemberWorkoutAssignService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public MemberWorkoutAssignResponse assign(@RequestBody MemberWorkoutAssignRequest req) {
        return service.assignWorkout(req);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public MemberWorkoutAssignResponse update(@PathVariable Long id, @RequestBody MemberWorkoutAssignRequest req) {
        return service.updateAssignment(id, req);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public void delete(@PathVariable Long id) {
        service.removeAssignment(id);
    }

    @GetMapping("/members/{memberId}")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER','MEMBER')")
    public List<MemberWorkoutAssignResponse> getByMember(@PathVariable Long memberId) {
        return service.getAssignmentsByMember(memberId);
    }
}
