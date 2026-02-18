package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.dtos.MemberWorkoutAssignRequest;
import com.gymmanagement.gym_management.dtos.MemberWorkoutAssignResponse;

public interface MemberWorkoutAssignService {

    MemberWorkoutAssignResponse assignWorkout(MemberWorkoutAssignRequest req);
    MemberWorkoutAssignResponse updateAssignment(Long id, MemberWorkoutAssignRequest req);
    void removeAssignment(Long id);
    List<MemberWorkoutAssignResponse> getAssignmentsByMember(Long memberId);
}
