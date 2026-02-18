package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.entities.CourseEnrollment;
import com.gymmanagement.gym_management.entities.Order;

public interface CourseEnrollmentService {
    void enrollCourse(Order order);

    List<CourseEnrollment> getMyEnrollments(Long memberId);
}
