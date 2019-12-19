package com.project.server.services;

import com.project.server.services.exceptions.UserNotFoundException;
import com.project.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService {

    @Autowired
    UserService service;

    public User loginValidate(User user) {
        User match = service.getUserByEmail(user.getEmail());
        if (user.getPassword().equals(match.getPassword())) {
            return match;
        } else {
            throw new UserNotFoundException(user);
        }
    }
}