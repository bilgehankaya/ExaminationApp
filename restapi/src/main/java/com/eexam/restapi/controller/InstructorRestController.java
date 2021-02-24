package com.eexam.restapi.controller;

import com.eexam.restapi.converter.InstructorConverter;
import com.eexam.restapi.entity.Instructor;
import com.eexam.restapi.payload.InstructorDto;
import com.eexam.restapi.service.InstructorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InstructorRestController {

    @Autowired
    InstructorService InstructorService;

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/instructors")
    public InstructorDto getInstructor() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Instructor instructor = InstructorService.getByUsername(username);
        if(instructor == null) {
            throw new IllegalStateException("User could not be found");
        } 

        return InstructorConverter.getInstuctorDto(instructor);
    }
}