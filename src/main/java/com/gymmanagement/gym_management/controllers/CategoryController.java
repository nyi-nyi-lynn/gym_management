package com.gymmanagement.gym_management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym_management.dtos.CategoryResponse;
import com.gymmanagement.gym_management.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/categories")
public class CategoryController{
    @Autowired
    private CategoryService service;


    @GetMapping
    public List<CategoryResponse> getActive() {
        return service.getActive();
    }
    
}
