package com.gymmanagement.gym_management.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.gymmanagement.gym_management.enums.AssignStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_workout_plan_assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberWorkoutPlanAssign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id", nullable = false)
    private WorkoutPlan workoutPlan;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private AssignStatus status;

    private LocalDateTime createdAt ;

    @PrePersist
    public void onCrate(){
        this.createdAt = LocalDateTime.now();
    }
}
