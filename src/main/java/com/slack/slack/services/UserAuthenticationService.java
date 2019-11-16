package com.slack.slack.services;

import com.slack.slack.dao.models.User;
import com.slack.slack.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public boolean isAuthenticated(String login, String password){
        User user = getUser(login);

        return user.getPassword().equals(password);
    }

    private User getUser(String login) {
        User user = userRepository.getUserByLogin(login);
        if (user == null) {
            throw new NullPointerException("User does not exist!");
        }

        return user;
    }
}
