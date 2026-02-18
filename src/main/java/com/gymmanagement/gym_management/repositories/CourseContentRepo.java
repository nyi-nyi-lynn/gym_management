package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.CourseContent;

@Repository
public interface CourseContentRepo extends JpaRepository<CourseContent,Long> {
    List<CourseContent> findByCourse_Id(Long courseId);
}
