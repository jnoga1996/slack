package com.slack.slack.controllers;

import com.slack.slack.dao.models.Course;
import com.slack.slack.dao.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Course get(@PathVariable("id") Long id) {
        Course course = courseRepository.getOne(id);
        if (course == null) {
            throw new NullPointerException("Course not found!");
        }

        return course;
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
