package com.slack.slack.controllers;

import com.slack.slack.dao.models.Attendance;
import com.slack.slack.dao.models.AttendanceList;
import com.slack.slack.dao.models.Pair;
import com.slack.slack.dao.models.User;
import com.slack.slack.dao.repositories.AttendanceListRepository;
import com.slack.slack.dao.repositories.AttendanceRepository;
import com.slack.slack.dao.repositories.UserRepository;
import com.slack.slack.dto.AttendanceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Attendance")
public class AttendanceController {

    private AttendanceListRepository attendanceListRepository;
    private AttendanceRepository attendanceRepository;
    private UserRepository userRepository;

    @Autowired
    public AttendanceController(AttendanceListRepository attendanceListRepository,
                                AttendanceRepository attendanceRepository,
                                UserRepository userRepository) {
        this.attendanceListRepository = attendanceListRepository;
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/All")
    public List<AttendanceList> getAll() {
        return attendanceListRepository.findAll();
    }

    @GetMapping("/Dates")
    public List<Pair<Long, LocalDate>> getAllDates(@RequestParam Long courseId) {
        List<Tuple> data = attendanceListRepository.findAllDatesByCourseId(courseId);
        List<Pair<Long, LocalDate>> convertedData = new ArrayList<>();
        for (Tuple tuple : data) {
            LocalDate parsedDate = LocalDate.parse(tuple.get(1).toString(), DateTimeFormatter.ISO_DATE);
            Long attendanceListId = Long.parseLong(tuple.get(0).toString());
            Pair<Long, LocalDate> pair = new Pair(attendanceListId, parsedDate);
            convertedData.add(pair);
        }
        return convertedData;
    }

    @GetMapping("/Test")
    public AttendanceWrapper test() {
        AttendanceWrapper wrapper = new AttendanceWrapper();
        wrapper.setDateString("2019-12-30");
        Map<Long, Pair<Boolean, Boolean>> test = new HashMap<>();
        test.put(1L, new Pair(Boolean.TRUE, Boolean.FALSE));
        test.put(2L, new Pair(Boolean.TRUE, Boolean.FALSE));
        test.put(3L, new Pair(Boolean.FALSE, Boolean.TRUE));
        wrapper.setPresentUsers(test);

        return wrapper;
    }


    @GetMapping("/")
    public List<AttendanceList> getAllByDateAndCourse(@RequestParam String date, @RequestParam Long courseId) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        return attendanceListRepository.findAllByDateAndCourseId(localDate, courseId);
    }

    /*
    Default post request
    content-type : application/json
    body :
    {
	    "dateString":"2019-12-31",
	    "presentUsers":{"1":{"firstValue":true, "secondValue":false},"2":{"firstValue":true, "secondValue":false},"3":{"firstValue":false, "secondValue":true}},
	    "courseId" : 5
    }
     */
    @PostMapping(value = "/", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Attendance> create(@RequestBody AttendanceWrapper activity) {
        if (activity == null) {
            throw new NullPointerException("Activity is null!");
        }

        AttendanceList attendanceList = new AttendanceList();
        List<Attendance> attendances = new ArrayList<>();
        attendanceListRepository.save(attendanceList);
        for (Long userId : activity.getPresentUsers().keySet()) {
            Attendance attendance = new Attendance();
            User user = userRepository.getOne(userId);
            attendance.setUser(user);
            Pair<Boolean, Boolean> pair = activity.getPresentUsers().get(user.getId());
            attendance.setPresent(pair.getFirstValue());
            attendance.setWantsToBeSigned(pair.getSecondValue());
            LocalDate localDate = LocalDate.parse(activity.getDateString(), DateTimeFormatter.ISO_DATE);
            attendance.setDateTime(localDate);
            attendance.setAttendanceList(attendanceList);
            if (user != null && localDate != null && attendanceList != null) {
                attendanceRepository.save(attendance);
                attendances.add(attendance);
            } else {
                continue;
            }
        }
        attendanceList.setAttendances(attendances);
        attendanceList.setCourseId(activity.getCourseId());
        attendanceListRepository.save(attendanceList);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Attendance> update(@RequestBody AttendanceWrapper activity) {
        if (activity == null) {
            throw new NullPointerException("Activity is null!");
        } else if (activity.getAttendanceListId() == null) {
            throw new NullPointerException("ActivityListId is null!");
        }

        AttendanceList attendanceList = attendanceListRepository.getOne(activity.getAttendanceListId());
        List<Attendance> attendances = new ArrayList<>();
        for (Long userId : activity.getPresentUsers().keySet()) {
            Attendance attendance = new Attendance();
            User user = userRepository.getOne(userId);
            attendance.setUser(user);
            Pair<Boolean, Boolean> pair = activity.getPresentUsers().get(user.getId());
            attendance.setPresent(pair.getFirstValue());
            attendance.setWantsToBeSigned(pair.getSecondValue());
            LocalDate localDate = LocalDate.parse(activity.getDateString(), DateTimeFormatter.ISO_DATE);
            attendance.setDateTime(localDate);
            attendance.setAttendanceList(attendanceList);
            if (user != null && localDate != null && attendanceList != null) {
                attendanceRepository.save(attendance);
                attendances.add(attendance);
            } else {
                continue;
            }
        }
        attendanceList.setAttendances(attendances);
        attendanceList.setCourseId(activity.getCourseId());
        attendanceListRepository.save(attendanceList);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AttendanceList> delete(@PathVariable("id") Long id) {
        AttendanceList attendanceList = attendanceListRepository.getOne(id);
        if (attendanceList != null) {
            for (Attendance attendance : attendanceList.getAttendances()) {
                attendanceRepository.delete(attendance);
            }
            attendanceListRepository.delete(attendanceList);
            return new ResponseEntity(attendanceList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
