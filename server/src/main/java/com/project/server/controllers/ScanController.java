package com.project.server.controllers;

import java.util.List;

import com.project.server.model.Scan;
import com.project.server.model.User;
import com.project.server.services.ScanService;
import com.project.server.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class ScanController {

    @Autowired
    ScanService service;

    @GetMapping("/testScan")
    public List<Scan> getTest() {
        return service.getScanByProject("project1");
    }

    @GetMapping("/scans")
    public List<Scan> getScanForUser(@RequestHeader ("Email") String email) {
        return service.getScanByEmail(email);
    }

    @GetMapping("/scans_project")
    public List<Scan> getScanForProject(@RequestHeader ("Project") String projectName) {
        return service.getScanByProject(projectName);
    }
}