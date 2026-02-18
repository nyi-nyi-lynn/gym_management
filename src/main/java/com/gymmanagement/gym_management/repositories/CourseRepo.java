package com.gymmanagement.gym_management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.Course;
import com.gymmanagement.gym_management.enums.CourseStatus;

@Repository
public interface CourseRepo  extends JpaRepository<Course,Long>{
    Page<Course> findByStatus(CourseStatus status, Pageable pageable);

    Page<Course> findByCategory_IdAndStatus(
            Long categoryId,
            CourseStatus status,
            Pageable pageable
    );
}
