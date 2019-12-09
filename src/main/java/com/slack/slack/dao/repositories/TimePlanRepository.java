package com.slack.slack.dao.repositories;

import com.slack.slack.dao.models.Activity;
import com.slack.slack.dao.models.TimePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimePlanRepository extends JpaRepository<TimePlan, Long> {

    Optional<TimePlan> findByTimePlanId(Long id);

    Optional<TimePlan> findAllByActivity(Activity activity);
}
