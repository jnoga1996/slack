package com.slack.slack.dao.repositories;

import com.slack.slack.dao.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
