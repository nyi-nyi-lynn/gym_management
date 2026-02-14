package com.gymmanagement.gym_management.services;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.MemberProfileRequest;
import com.gymmanagement.gym_management.dtos.MemberResponse;
import com.gymmanagement.gym_management.entities.Member;
import com.gymmanagement.gym_management.entities.User;
import com.gymmanagement.gym_management.enums.UserRole;
import com.gymmanagement.gym_management.repositories.MemberRepo;
import com.gymmanagement.gym_management.repositories.UserRepo;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public MemberResponse completeProfile(Long userId, MemberProfileRequest request) {
        Member member = getMember(userId);
        member.setHeight(request.getHeight());
        member.setWeight(request.getWeight());
        member.setProfileCompleted(true);

        Member savedMember = memberRepo.save(member);

        return maptoResponse(savedMember);
    }

    @Override
    public MemberResponse getMyProfile(Long userId) {
        return maptoResponse(getMember(userId));
    }




    

    //Helper Methods
    private MemberResponse maptoResponse(Member member){
        MemberResponse response = new MemberResponse();
        response.setId(member.getId());
        response.setUserId(member.getUser().getId());
        response.setName(member.getUser().getName());
        response.setEmail(member.getUser().getEmail());
        response.setHeight(member.getHeight());
        response.setGender(member.getUser().getGender());
        response.setAge(Period.between(member.getUser().getDateOfBirth(), LocalDate.now()).getYears());
        response.setWeight(member.getWeight());
        response.setJoinDate(member.getJoinDate());
        response.setStatus(member.getStatus());

        return response;
    }


    private Member getMember(Long userId){
         User user = userRepo.findById(userId)
         .orElseThrow(()-> new RuntimeException("User not Found!"));

        if (!user.getRole().equals(UserRole.MEMBER)) {
            throw new RuntimeException("User is not MEMBER role");
        }
        return memberRepo.findByUserId(userId)
        .orElseThrow(()-> new RuntimeException("Member Not Found!"));
    }

}
