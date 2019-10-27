package com.slack.slack;

import com.slack.slack.dao.models.Message;
import com.slack.slack.dao.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class SlackApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SlackApplication.class);

    @Autowired
    private MessageRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SlackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("App started");
        repository.save(new Message(1L, "Message 1"));
        repository.save(new Message(2L, "Message 2"));
        repository.save(new Message(3L, "Message 3"));
        repository.save(new Message(4L, "Message 4"));
    }
}
