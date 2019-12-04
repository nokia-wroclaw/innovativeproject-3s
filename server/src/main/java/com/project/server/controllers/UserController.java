package com.project.server.controllers;

import java.util.List;

import com.project.server.model.User;
import com.project.server.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired UserService service;
    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }
    @PostMapping
    public void postUser(@RequestBody User usr) {
        service.add(usr);
    }
     @GetMapping("/{id}")
    //  @GetMapping
    public User getById(@PathVariable(required = true) long id) {
        return  service.getUserById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }
}
