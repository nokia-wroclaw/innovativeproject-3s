package com.project.server.controllers;

import com.project.server.Application;
import com.project.server.controllers.exceptions.UserNotFoundException;
import com.project.server.model.User;
import com.project.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
    private final UserRepository repository;

    LoginController(UserRepository repository) {
        this.repository = repository;
    }
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/user/{id}")
    User fetchUser(@PathVariable Long id) {
        System.out.println("validateLogin <id> launched");
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @RequestMapping("/validateLogin")
    public User validateUser(@RequestBody User user) {
        System.out.println("validateLogin <user> launched");
        List<User> userList = repository.findByUsername(user.getUsername());
        for (User u : userList) {
            if (user.getPassword().equals(u.getPassword())) {
                return repository.findById(u.getId())
                        .orElseThrow(() -> new UserNotFoundException(user));
            }
        }

        throw new UserNotFoundException(user);
    }
}