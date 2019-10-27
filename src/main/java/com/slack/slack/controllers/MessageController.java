package com.slack.slack.controllers;

import com.slack.slack.dao.models.Message;
import com.slack.slack.dao.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Message")
public class MessageController {

    @Autowired
    private MessageRepository repository;

    @GetMapping("/Messages")
    public List<Message> getAll() {
        return repository.findAll();
    }

    @GetMapping("/Messages/{id}")
    public Message get(@PathVariable("id") Long id) {
        return repository.getOne(id);
    }

    @PostMapping("/Messages")
    public HttpStatus create(String content) {
        Message message = new Message(content);
        repository.save(message);

        return HttpStatus.OK;
    }

    @DeleteMapping("/Messages/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        Message message = repository.getOne(id);
        if (message != null) {
            repository.delete(message);
            return HttpStatus.OK;
        }

        return HttpStatus.NOT_FOUND;
    }
}
