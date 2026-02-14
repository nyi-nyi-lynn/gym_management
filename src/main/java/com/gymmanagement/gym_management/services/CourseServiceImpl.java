package com.gymmanagement.gym_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gymmanagement.gym_management.dtos.CourseRequest;
import com.gymmanagement.gym_management.dtos.CourseResponse;
import com.gymmanagement.gym_management.entities.Category;
import com.gymmanagement.gym_management.entities.Course;
import com.gymmanagement.gym_management.entities.Trainer;
import com.gymmanagement.gym_management.enums.CourseStatus;
import com.gymmanagement.gym_management.enums.FileType;
import com.gymmanagement.gym_management.repositories.CategoryRepo;
import com.gymmanagement.gym_management.repositories.CourseRepo;
import com.gymmanagement.gym_management.repositories.TrainerRepo;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private  CourseRepo courseRepo;

    @Autowired
    private  CategoryRepo categoryRepo;

    @Autowired
    private  TrainerRepo trainerRepo;

    @Autowired
    private  FileStorageService fileStorageService;

    @Override
    public CourseResponse create(CourseRequest req, MultipartFile thumbnail) {

        Category category = categoryRepo.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Course course = new Course();
        course.setCategory(category);
        course.setTitle(req.getTitle());
        course.setDescription(req.getDescription());
        course.setPrice(req.getPrice());
        course.setDurationDays(req.getDurationDays());
        course.setLevel(req.getLevel());
        course.setStatus(CourseStatus.ACTIVE);

        if (req.getTrainerId() != null) {
            Trainer trainer = trainerRepo.findById(req.getTrainerId())
                    .orElseThrow(() -> new RuntimeException("Trainer not found"));
            course.setTrainer(trainer);
        }

        if (thumbnail != null && !thumbnail.isEmpty()) {
            String url = fileStorageService.store(thumbnail, FileType.IMAGE);
            course.setThumbnailUrl(url);
        }

        courseRepo.save(course);

          return mapToResponse(course);
    }

    @Override
    public CourseResponse update(Long id, CourseRequest req, MultipartFile thumbnail) {

        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Category category = categoryRepo.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        course.setCategory(category);
        course.setTitle(req.getTitle());
        course.setDescription(req.getDescription());
        course.setPrice(req.getPrice());
        course.setDurationDays(req.getDurationDays());
        course.setLevel(req.getLevel());

        if (thumbnail != null && !thumbnail.isEmpty()) {
            String url = fileStorageService.store(thumbnail, FileType.IMAGE);
            course.setThumbnailUrl(url);
        }
        courseRepo.save(course);
        return mapToResponse(course);
    }

    @Override
    public void deactivate(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setStatus(CourseStatus.INACTIVE);

        courseRepo.save(course);
    }

    @Override
    public Page<CourseResponse> getAllActive(Pageable pageable) {
        return courseRepo.findByStatus(CourseStatus.ACTIVE, pageable)
                .map(this::mapToResponse);
    }

    @Override
    public Page<CourseResponse> getByCategory(Long categoryId, Pageable pageable) {
        return courseRepo
                .findByCategory_IdAndStatus(categoryId, CourseStatus.ACTIVE, pageable)
                .map(this::mapToResponse);
    }


    //Helper Methods 

    /**
     * 
     * @param course
     * @return
     */
    private CourseResponse mapToResponse(Course course) {
        CourseResponse res = new CourseResponse();
        res.setId(course.getId());
        res.setTitle(course.getTitle());
        res.setDescription(course.getDescription());
        res.setPrice(course.getPrice());
        res.setDurationDays(course.getDurationDays());
        res.setLevel(course.getLevel());
        res.setThumbnailUrl(course.getThumbnailUrl());
        res.setStatus(course.getStatus());
        res.setCreatedAt(course.getCreatedAt());
        res.setCategoryId(course.getCategory().getId());
        res.setCategoryName(course.getCategory().getName());

        if (course.getTrainer() != null) {
            res.setTrainerId(course.getTrainer().getId());
        }

        return res;
    }

}
