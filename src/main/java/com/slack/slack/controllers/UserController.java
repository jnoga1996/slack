package com.slack.slack.controllers;

import com.slack.slack.dao.models.User;
import com.slack.slack.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/Users")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/User/{id}")
    public User getUser(@PathVariable("id") Long id) {
        User user = userRepository.getOne(id);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        return user;
    }

    @PostMapping("/User")
    public HttpStatus createUser(String login, String password, String faculty, String major, int semester) {
        User user = new User(login, password, faculty, major, semester);
        userRepository.save(user);

        return HttpStatus.OK;
    }

    @DeleteMapping("User/{id}")
    public HttpStatus deleteUser(@PathVariable("id") Long id) {
        User user = userRepository.getOne(id);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        userRepository.delete(user);

        return HttpStatus.OK;
    }
}
