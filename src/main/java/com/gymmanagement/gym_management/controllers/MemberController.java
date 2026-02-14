package com.gymmanagement.gym_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.MemberProfileRequest;
import com.gymmanagement.gym_management.dtos.MemberResponse;
import com.gymmanagement.gym_management.services.MemberService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PutMapping("/profile")
    public MemberResponse completeProfile(
            @RequestParam Long userId,
            @RequestBody MemberProfileRequest request) {

        return  memberService.completeProfile(userId, request);
    }

   @GetMapping("/me")
   public MemberResponse getMyProfile(@RequestParam Long userId) {
       return memberService.getMyProfile(userId);
   }
   
}
