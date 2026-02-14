package com.gymmanagement.gym_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.dtos.LoginRequestDTO;
import com.gymmanagement.gym_management.dtos.RegisterRequest;
import com.gymmanagement.gym_management.entities.Member;
import com.gymmanagement.gym_management.entities.Trainer;
import com.gymmanagement.gym_management.entities.User;
import com.gymmanagement.gym_management.enums.AccountStatus;
import com.gymmanagement.gym_management.enums.EmploymentStatus;
import com.gymmanagement.gym_management.enums.Gender;
import com.gymmanagement.gym_management.enums.MembershipStatus;
import com.gymmanagement.gym_management.enums.UserRole;
import com.gymmanagement.gym_management.repositories.MemberRepo;
import com.gymmanagement.gym_management.repositories.TrainerRepo;
import com.gymmanagement.gym_management.repositories.UserRepo;
import com.gymmanagement.gym_management.security.JwtUtil;
import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private TrainerRepo trainerRepo;

    @Override
    public String login(LoginRequestDTO dto) {
       User user = userRepo.findByEmail(dto.getEmail())
       .orElseThrow(()-> new RuntimeException("Invalid email"));

        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

       return jwtUtil.generateToken(user.getEmail(), user.getRole().toString());
    }

    @Override
    public void registerMember(RegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = buildUser(request, UserRole.MEMBER);
        User savedUser = userRepo.save(user);

        Member member = new Member();
        member.setUser(savedUser);
        member.setStatus(MembershipStatus.INACTIVE);
        member.setJoinDate(LocalDate.now());
        member.setProfileCompleted(false);
        memberRepo.save(member);
    }


    @Override
    public void registerTrainer(RegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = buildUser(request, UserRole.TRAINER);
        User savedUser = userRepo.save(user);

        Trainer trainer = new Trainer();
        trainer.setUser(savedUser);
        trainer.setStatus(EmploymentStatus.INACTIVE);
        trainer.setStartWorkDate(LocalDate.now());
        trainer.setProfileCompleted(false);
        trainerRepo.save(trainer);
    }



    /**
     * Helper Methods
     */


    private User buildUser(RegisterRequest request, UserRole role) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setGender(Gender.valueOf(request.getGender().toUpperCase()));
        user.setDateOfBirth(request.getDateOfBirth());
        user.setRole(role);
        user.setStatus(AccountStatus.ACTIVE);
        return user;
    }


}
