package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gymmanagement.gym_management.dtos.MemberWorkoutAssignRequest;
import com.gymmanagement.gym_management.dtos.MemberWorkoutAssignResponse;
import com.gymmanagement.gym_management.services.MemberWorkoutAssignService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member-workout-assign")
@RequiredArgsConstructor
public class MemberWorkoutAssignController {

    private final MemberWorkoutAssignService service;

    @PostMapping
    public MemberWorkoutAssignResponse assign(@RequestBody MemberWorkoutAssignRequest req) {
        return service.assignWorkout(req);
    }

    @PutMapping("/{id}")
    public MemberWorkoutAssignResponse update(@PathVariable Long id, @RequestBody MemberWorkoutAssignRequest req) {
        return service.updateAssignment(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeAssignment(id);
    }

    @GetMapping("/member/{memberId}")
    public List<MemberWorkoutAssignResponse> getByMember(@PathVariable Long memberId) {
        return service.getAssignmentsByMember(memberId);
    }
}
