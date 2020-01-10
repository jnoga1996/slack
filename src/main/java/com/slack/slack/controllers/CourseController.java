package com.slack.slack.controllers;

import com.slack.slack.dao.models.Course;
import com.slack.slack.dao.repositories.CourseRepository;
import com.slack.slack.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Courses")
public class CourseController {

    private CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/")
    public List<CourseDTO> getAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    @GetMapping("/{id}")
    public CourseDTO get(@PathVariable("id") Long id) {
        Course course = courseRepository.getOne(id);
        if (course == null) {
            throw new NullPointerException("Course not found!");
        }

        CourseDTO courseDTO = new CourseDTO(course);
        return courseDTO;
    }

    @PostMapping(value = "/", consumes = "application/json")
    @ResponseBody
    public HttpStatus create(@RequestBody Course course) {
        if (course == null) {
            throw new NullPointerException("Course is null!");
        }
        courseRepository.save(course);

        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        Course course = courseRepository.getOne(id);
        if (course != null) {
            courseRepository.delete(course);
            return HttpStatus.OK;
        }

        return HttpStatus.NOT_FOUND;
    }
}
