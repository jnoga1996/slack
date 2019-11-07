package com.slack.slack.dao.repositories;

import com.slack.slack.dao.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
