package com.gymmanagement.gym_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.MemberProfileRequest;
import com.gymmanagement.gym_management.dtos.MemberResponse;
import com.gymmanagement.gym_management.security.CustomUserDetails;
import com.gymmanagement.gym_management.services.MemberService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PutMapping("/me/profile")
    @PreAuthorize("hasRole('MEMBER')")
    public MemberResponse completeProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody MemberProfileRequest request) {

        return  memberService.completeProfile(userDetails.getId(), request);
    }

   @GetMapping("/me")
   @PreAuthorize("hasRole('MEMBER')")
   public MemberResponse getMyProfile(
           @AuthenticationPrincipal CustomUserDetails userDetails) {
       return memberService.getMyProfile(userDetails.getId());
   }
   
}
