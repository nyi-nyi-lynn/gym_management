package com.gymmanagement.gym_management.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym_management.entities.Category;
import com.gymmanagement.gym_management.enums.CategoryStatus;

@Repository
public interface CategoryRepo  extends JpaRepository<Category,Long>{
     boolean existsByNameIgnoreCase(String name);

    @Query("""
        SELECT c FROM Category c
        WHERE (:keyword IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
          AND (:status IS NULL OR c.status = :status)
    """)
    Page<Category> search(
        @Param("keyword") String keyword,
        @Param("status") CategoryStatus status,
        Pageable pageable
    );

    List<Category> findByStatus(CategoryStatus status);
}
