package com.slack.slack.controllers;

import com.slack.slack.dao.models.Activity;
import com.slack.slack.dao.models.TimePlan;
import com.slack.slack.dao.repositories.TimePlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TimePlan")
@RequiredArgsConstructor
public class TimePlanController {

    private TimePlanRepository timePlanRepository;

    @GetMapping("/Activity")
    public TimePlan getTimePlanByActivity(
            @RequestBody Activity activity) {
        return timePlanRepository.findAllByActivity(activity)
                .orElseThrow(() -> new  IllegalArgumentException("No Time Plan for activity:" + activity));
    }
    @GetMapping("/{id}")
    public TimePlan getTimePlan(@PathVariable("id") Long id){
        return timePlanRepository.findByTimePlanId(id)
                .orElseThrow(() -> new IllegalArgumentException("No Time Plan with id:" + id));
    }
    @PostMapping
    public void addTimePlan(@RequestBody TimePlan timePlan){
        timePlanRepository.save(timePlan);
    }
    @DeleteMapping("/{id}")
    public void deleteTimePlan (@PathVariable("id") Long id) {
        TimePlan timePlan = timePlanRepository.findByTimePlanId(id)
                .orElseThrow(() -> new IllegalArgumentException("No Time Plan with id:" + id));
        timePlanRepository.delete(timePlan);
    }

}
