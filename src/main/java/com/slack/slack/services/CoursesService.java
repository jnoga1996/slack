package com.slack.slack.services;


import com.slack.slack.dao.models.Courses2;
import com.slack.slack.dao.repositories.Courses2Repository;
import com.slack.slack.dto.Courses2DTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CoursesService {

    CoursesService(Courses2Repository courses2Repository){
        this.courses2Repository = courses2Repository;
    }

    public final Courses2Repository courses2Repository;

    Optional<List<Courses2>> getCourses(Long userId, LocalDateTime courseStartDate){
        return courses2Repository.findAllByOwnerIdAndAndStartDate(userId, courseStartDate);
    }

    public void saveCourse(Courses2DTO courses2DTO) {
    }
}
