package com.gymmanagement.gym_management.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gymmanagement.gym_management.dtos.UserCreateRequest;
import com.gymmanagement.gym_management.dtos.UserResponse;
import com.gymmanagement.gym_management.dtos.UserUpdateRequest;
import com.gymmanagement.gym_management.entities.Member;
import com.gymmanagement.gym_management.entities.Trainer;
import com.gymmanagement.gym_management.entities.User;
import com.gymmanagement.gym_management.enums.UserRole;
import com.gymmanagement.gym_management.enums.AccountStatus;
import com.gymmanagement.gym_management.enums.EmploymentStatus;
import com.gymmanagement.gym_management.enums.Gender;
import com.gymmanagement.gym_management.enums.MembershipStatus;
import com.gymmanagement.gym_management.repositories.MemberRepo;
import com.gymmanagement.gym_management.repositories.TrainerRepo;
import com.gymmanagement.gym_management.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private TrainerRepo trainerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserCreateRequest request) {
       
        if(userRepo.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already in use");
        }
         User user = new User();
         user.setName(request.getName());
         user.setEmail(request.getEmail());
         user.setPassword(passwordEncoder.encode(request.getPassword()));
         user.setPhoneNumber(request.getPhoneNumber());
         user.setAddress(request.getAddress());
         user.setGender(Gender.valueOf(request.getGender().toUpperCase()));
         user.setDateOfBirth(request.getDateOfBirth());
         user.setRole(UserRole.valueOf(request.getRole().toUpperCase()));
         user.setStatus(AccountStatus.ACTIVE);
         User savedUser = userRepo.save(user);

         if(savedUser.getRole() == UserRole.MEMBER){
            createMemer(savedUser);
         }

         if(savedUser.getRole() == UserRole.TRAINER){
            createTrainer(savedUser);
         }
         
      return mapToResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = getUserEntity(id);
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setGender(Gender.valueOf(request.getGender().toUpperCase()));
        user.setDateOfBirth(request.getDateOfBirth());
        user.setRole(request.getRole());

        return mapToResponse(userRepo.save(user));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        return mapToResponse(
            getUserEntity(id)
        );
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserEntity(id);
        userRepo.delete(user);
    }

    @Override
    public void activateUser(Long id) {
        updateStatus(id, AccountStatus.ACTIVE);
    }

    @Override
    public void deactivateUser(Long id) {
        updateStatus(id, AccountStatus.INACTIVE);
    }

    @Override
    public void banUser(Long id) {
        updateStatus(id, AccountStatus.SUSPENDED);
    }

    //Helper Methods
    
    private void updateStatus(Long id, AccountStatus status) {
        User user = getUserEntity(id);
        user.setStatus(status);
        userRepo.save(user);
    }

    //to find User with id
    private User getUserEntity(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    //to create user response object
    private UserResponse mapToResponse(User user){
        UserResponse response = new UserResponse();
         response.setId(user.getId());
         response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setPhoneNumber(user.getPhoneNumber());
            response.setAddress(user.getAddress());
            response.setGender(user.getGender());
            response.setDateOfBirth(user.getDateOfBirth());
            response.setRole(user.getRole());   
            response.setStatus(user.getStatus());
      return response;
    }


     private void createTrainer(User user) {
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setStatus(EmploymentStatus.INACTIVE);
        trainer.setStartWorkDate(LocalDate.now());
        trainerRepo.save(trainer);
    }

    private void createMemer(User user) {
        Member member = new Member();
        member.setUser(user);
        member.setStatus(MembershipStatus.INACTIVE);
        member.setJoinDate(LocalDate.now());

        memberRepo.save(member);
        
    }
}
