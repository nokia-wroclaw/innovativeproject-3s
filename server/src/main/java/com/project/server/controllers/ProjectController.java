package com.project.server.controllers;

import com.project.server.model.Project;
import com.project.server.model.Scan;
import com.project.server.services.ProjectService;
import com.project.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class ProjectController {
    @Autowired
    ProjectService service;

    @GetMapping("/projectTest")
    public List<Project> getProjectTest() {
        return service.getProjectByUserEmail("admin");
    }

    @GetMapping("/projects")
    public List<Project> getProjectsForUser(@RequestHeader("Email") String email) {
        return service.getProjectByUserEmail(email);
    }
}
