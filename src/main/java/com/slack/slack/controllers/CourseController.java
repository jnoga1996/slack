package com.slack.slack.controllers;

import com.slack.slack.dao.models.Course;
import com.slack.slack.dao.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("Courses")
public class CourseController {

    private CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/Courses")
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @GetMapping("/Courses/{id}")
    public ResponseEntity<Course> get(@PathVariable("id") Long id) {
        Course course = courseRepository.getOne(id);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/Courses")
    public HttpStatus create(Course course) {
        if (course == null) {
            return HttpStatus.NOT_FOUND;
        }
        if (course.getStartDate() == null) {
            course.setStartDate(LocalDate.now());
        }
        if (course.getEndDate() == null) {
            course.setEndDate(LocalDate.now().plusWeeks(15));
        }
        courseRepository.save(course);

        return HttpStatus.OK;
    }

    @DeleteMapping("/Courses/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        Course course = courseRepository.getOne(id);
        if (course != null) {
            courseRepository.delete(course);
            return HttpStatus.OK;
        }

        return HttpStatus.NOT_FOUND;
    }
}
