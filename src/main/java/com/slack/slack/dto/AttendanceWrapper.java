package com.slack.slack.dto;

import com.slack.slack.dao.models.User;

import java.util.HashMap;
import java.util.Map;

public class AttendanceWrapper {

    private String dateString;
    private Map<Long, Boolean> presentUsers = new HashMap<>();

    public AttendanceWrapper() {}

    public AttendanceWrapper(String dateString, Map<Long, Boolean> presentUsers) {
        this.dateString = dateString;
        this.presentUsers = presentUsers;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Map<Long, Boolean> getPresentUsers() {
        return presentUsers;
    }

    public void setPresentUsers(Map<Long, Boolean> presentUsers) {
        this.presentUsers = presentUsers;
    }
}
