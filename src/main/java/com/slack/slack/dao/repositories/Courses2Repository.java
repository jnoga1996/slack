package com.slack.slack.dao.repositories;

import com.slack.slack.dao.models.Courses2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface Courses2Repository extends JpaRepository<Courses2, Long> {

    Optional<List<Courses2>> findAllByOwnerIdAndAndStartDate(Long userId, LocalDateTime startDate);
}
