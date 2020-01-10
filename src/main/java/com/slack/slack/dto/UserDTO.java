package com.slack.slack.dto;

import com.slack.slack.dao.models.Course;
import com.slack.slack.dao.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private User user;
    private List<Long> courses;

    public UserDTO(User user) {
        this.user = user;
        courses = new ArrayList<>();
        for (Course course : user.getCourses()) {
            courses.add(course.getId());
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Long> getCourses() {
        return courses;
    }

    public void setCourses(List<Long> courses) {
        this.courses = courses;
    }
}
