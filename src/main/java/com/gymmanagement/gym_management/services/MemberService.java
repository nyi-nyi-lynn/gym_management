package com.gymmanagement.gym_management.services;


import com.gymmanagement.gym_management.dtos.MemberProfileRequest;
import com.gymmanagement.gym_management.dtos.MemberResponse;

public interface MemberService {
    MemberResponse completeProfile(Long userId , MemberProfileRequest request);


    MemberResponse getMyProfile(Long userId);
   

}
