package com.slack.slack.controllers;

import com.slack.slack.dao.models.User;
import com.slack.slack.dao.repositories.UserRepository;
import com.slack.slack.dto.UserDTO;
import com.slack.slack.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Users")
public class UserController {

    private UserRepository userRepository;
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    public UserController(UserRepository userRepository, UserAuthenticationService userAuthenticationService) {
        this.userRepository = userRepository;
        this.userAuthenticationService = userAuthenticationService;
    }

    @GetMapping("/All")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/All2")
    public List<UserDTO> getAllDTOs() {
        List<User> users = userRepository.findAll();
        List<UserDTO> transformedUsers = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user);
            transformedUsers.add(userDTO);
        }
        return transformedUsers;
    }

    @GetMapping("/")
    public UserDTO getUser(@RequestParam Map<String, String> params) {
        if (!params.containsKey("login") || !params.containsKey("password")) {
            throw new IllegalStateException("Incorrect request, should contain login and password!");
        }

        String login = params.get("login");
        String password = params.get("password");
        User user = userRepository.getUserByLogin(login);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        if (!userAuthenticationService.isAuthenticated(login, password)) {
            throw new IllegalStateException("Couldn't authenticate!");
        }

        UserDTO userDTO = new UserDTO(user);

        return userDTO;
    }

    @GetMapping("/Authenticate")
    public boolean getAuthenticated(@RequestParam Map<String, String> params) {
        if (!params.containsKey("login") || !params.containsKey("password")) {
            throw new IllegalStateException("Incorrect request, should contain login and password!");
        }

        String login = params.get("login");
        String password = params.get("password");

        return userAuthenticationService.isAuthenticated(login, password);
    }

    @PostMapping("/")
    public HttpStatus createUser(String login, String password, String faculty, String major, int semester) {
        User user = new User(login, password, faculty, major, semester);
        userRepository.save(user);

        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable("id") Long id) {
        User user = userRepository.getOne(id);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        userRepository.delete(user);

        return HttpStatus.OK;
    }
}
