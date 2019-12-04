package com.project.server.controllers;

import java.util.List;

import com.project.server.model.Tool;
import com.project.server.services.ToolService;

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
@RequestMapping("/tools")

public class ToolController {

    @Autowired ToolService service;
    @GetMapping
    public List<Tool> getTools() {
        return service.getTools();
    }
    @PostMapping
    public void postTool(@RequestBody Tool tool) {
        service.add(tool);
    }
    // @GetMapping("/{id}")
     @GetMapping("/{id}")
    public Tool getById(@PathVariable(required = true) long id) {
        return  service.getToolById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }
}