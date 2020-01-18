package com.slack.slack;

import com.slack.slack.storage.StorageProperties;
import com.slack.slack.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SlackApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SlackApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SlackApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("App started");
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
}
