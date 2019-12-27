package com.slack.slack.controllers;

import com.slack.slack.dao.models.Activity;
import com.slack.slack.dao.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Activities")
public class ActivityController {

    private ActivityRepository activityRepository;

    @Autowired
    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping("/")
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> get(@PathVariable("id") Long id) {
        Activity activity = activityRepository.getOne(id);
        if (activity == null) {
            throw new NullPointerException("Activity not found!");
        }

        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Activity> create(@RequestBody Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Activity is null!");
        }

        activityRepository.save(activity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Activity> delete(@PathVariable("id") Long id) {
        Activity activity = activityRepository.getOne(id);
        if (activity != null) {
            activityRepository.delete(activity);
            return new ResponseEntity<>(activity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
