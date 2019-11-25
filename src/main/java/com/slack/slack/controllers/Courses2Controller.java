package com.slack.slack.controllers;


import com.slack.slack.dto.Courses2DTO;
import com.slack.slack.services.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Cources2")
@RequiredArgsConstructor
public class Courses2Controller {

    private CoursesService coursesService;
    @Autowired
    Courses2Controller(CoursesService coursesService){
        this.coursesService = coursesService;
    }

    @PostMapping
    public void addCourse(
            @RequestBody Courses2DTO courses2DTO){
        coursesService.saveCourse(courses2DTO);

    }

}
