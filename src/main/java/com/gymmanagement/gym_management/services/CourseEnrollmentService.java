package com.gymmanagement.gym_management.services;

import java.util.List;

import com.gymmanagement.gym_management.entities.CourseEnrollment;
import com.gymmanagement.gym_management.entities.Orders;

public interface CourseEnrollmentService {
    void enrollCourse(Orders order);

    List<CourseEnrollment> getMyEnrollments(Long memberId);
}
