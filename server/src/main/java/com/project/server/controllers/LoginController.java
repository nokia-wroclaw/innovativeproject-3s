package com.project.server.controllers;

import com.project.server.model.User;
import com.project.server.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
    @Autowired
    LoginService service;

    @RequestMapping("/validateLogin")
    public User validateUser(@RequestBody User user) {
        System.out.println("validateLogin (" + user.getEmail() + ") launched");
        return service.loginValidate(user);
    }
}