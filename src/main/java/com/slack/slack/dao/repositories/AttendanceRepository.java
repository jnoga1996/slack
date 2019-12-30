package com.slack.slack.dao.repositories;

import com.slack.slack.dao.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query(value = "select max(id) from attendance", nativeQuery = true)
    Long getMaxId();
}
