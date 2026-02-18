package com.gymmanagement.gym_management.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_management.entities.Course;
import com.gymmanagement.gym_management.entities.CourseEnrollment;
import com.gymmanagement.gym_management.entities.Member;
import com.gymmanagement.gym_management.entities.Order;
import com.gymmanagement.gym_management.enums.EnrollmentStatus;
import com.gymmanagement.gym_management.repositories.CourseEnrollmentRepo;
import com.gymmanagement.gym_management.repositories.CourseRepo;
import com.gymmanagement.gym_management.repositories.MemberRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {
    private final MemberRepo memberRepo;
    private final CourseRepo courseRepo;
    private final CourseEnrollmentRepo enrollmentRepo;

    @Override
    public void enrollCourse(Order order){

        Member member = memberRepo
                .findByUserId(order.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Course course = courseRepo
                .findById(order.getReferenceId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        CourseEnrollment enrollment = new CourseEnrollment();
        enrollment.setOrder(order);
        enrollment.setCourse(course);
        enrollment.setMember(member);
        enrollment.setEnrollDate(LocalDate.now());
        enrollment.setExpireDate(LocalDate.now().plusDays(course.getDurationDays()));
        enrollment.setStatus(EnrollmentStatus.ACTIVE);

        enrollmentRepo.save(enrollment);
    }

    @Override
    public List<CourseEnrollment> getMyEnrollments(Long memberId){
        return enrollmentRepo.findByMemberId(memberId);
    }
}
