package com.slack.slack.dto;

import com.slack.slack.dao.models.Pair;

import java.util.HashMap;
import java.util.Map;

public class AttendanceWrapper {

    private String dateString;
    private Map<Long, Pair<Boolean, Boolean>> presentUsers = new HashMap<>();
    private Long courseId;
    private Long attendanceListId;

    public AttendanceWrapper() {}

    public AttendanceWrapper(String dateString, Map<Long, Pair<Boolean, Boolean>> presentUsers, Long courseId) {
        this.dateString = dateString;
        this.presentUsers = presentUsers;
        this.courseId = courseId;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Map<Long, Pair<Boolean, Boolean>> getPresentUsers() {
        return presentUsers;
    }

    public void setPresentUsers(Map<Long, Pair<Boolean, Boolean>> presentUsers) {
        this.presentUsers = presentUsers;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getAttendanceListId() {
        return attendanceListId;
    }

    public void setAttendanceListId(Long attendanceListId) {
        this.attendanceListId = attendanceListId;
    }
}
