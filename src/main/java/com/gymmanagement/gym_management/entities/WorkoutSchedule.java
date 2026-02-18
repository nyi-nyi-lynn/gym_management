package com.gymmanagement.gym_management.entities;


import com.gymmanagement.gym_management.enums.DayOfWeek;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workout_schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id", nullable = false)
    private WorkoutPlan workoutPlan;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private String exercise;

    private Integer sets;

    private Integer reps;
}
