package com.gymmanagement.gym_management.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gymmanagement.gym_management.dtos.CourseContentRequest;
import com.gymmanagement.gym_management.dtos.CourseContentResponse;
import com.gymmanagement.gym_management.entities.Course;
import com.gymmanagement.gym_management.entities.CourseContent;
import com.gymmanagement.gym_management.enums.ContentType;
import com.gymmanagement.gym_management.enums.FileType;
import com.gymmanagement.gym_management.repositories.CourseContentRepo;
import com.gymmanagement.gym_management.repositories.CourseRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseContentServiceImpl implements CourseContentService {
    private final CourseRepo courseRepo;
    private final CourseContentRepo contentRepo;
    private final FileStorageService fileStorageService;

    @Override
    public CourseContentResponse create(
            CourseContentRequest req,
            MultipartFile file) {

        Course course = courseRepo.findById(req.getCourseId())
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        CourseContent content = new CourseContent();
        content.setCourse(course);
        content.setTitle(req.getTitle());
        content.setContentType(req.getContentType());

        String url;

        if (req.getContentType() == ContentType.LINK) {

            if (req.getContentUrl() == null) {
                throw new RuntimeException("Link URL required");
            }
            url = req.getContentUrl();

        } else {

            if (file == null || file.isEmpty()) {
                throw new RuntimeException("File required");
            }

            if (req.getContentType() == ContentType.VIDEO) {
                url = fileStorageService.store(file, FileType.VIDEO);
            } else {
                url = fileStorageService.store(file, FileType.DOCUMENT);
            }
        }

        content.setContentUrl(url);

        contentRepo.save(content);

        return mapToResponse(content);
    }

    @Override
    public void delete(Long id) {

        CourseContent content = contentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Content not found"));

        contentRepo.delete(content);
    }

    @Override
    public List<CourseContentResponse> getByCourse(Long courseId) {

        return contentRepo.findByCourse_Id(courseId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    //Helper Methods


    /**
     * 
     * @param content
     * @return
     */
    private CourseContentResponse mapToResponse(CourseContent content) {

        CourseContentResponse res =
                new CourseContentResponse();

        res.setId(content.getId());
        res.setCourseId(content.getCourse().getId());
        res.setTitle(content.getTitle());
        res.setContentType(content.getContentType());
        res.setContentUrl(content.getContentUrl());

        return res;
    }
}
