package com.slack.slack.dto;

import com.slack.slack.dao.models.Activity;
import com.slack.slack.dao.models.Course;
import com.slack.slack.dao.models.User;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private Course course;
    private List<Long> users;
    private List<Long> activities;

    public CourseDTO(Course course) {
        this.course = course;
        users = new ArrayList<>();
        activities = new ArrayList<>();
        for (Activity activity : course.getActivities()) {
            activities.add(activity.getId());
        }
        for (User user : course.getUsers()) {
            users.add(user.getId());
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    public List<Long> getActivities() {
        return activities;
    }

    public void setActivities(List<Long> activities) {
        this.activities = activities;
    }
}
