package com.slack.slack.dao.repositories;

import com.slack.slack.dao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
