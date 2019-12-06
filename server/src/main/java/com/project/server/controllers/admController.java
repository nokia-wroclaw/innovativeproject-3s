package com.project.server.controllers;

import java.util.Collection;
import java.util.List;

import com.project.server.model.Project;
import com.project.server.model.Scan;
import com.project.server.model.Team;
import com.project.server.model.Tool;
import com.project.server.model.User;
import com.project.server.services.ProjectService;
import com.project.server.services.ScanService;
import com.project.server.services.TeamService;
import com.project.server.services.ToolService;
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
@RequestMapping("/admin")

public class admController {

    @Autowired UserService service;
    @GetMapping("/user")
    public List<User> getUsers() {
        return service.getUsers();
    }
    
    @PostMapping("/user")
    public void postUser(@RequestBody User usr) {
        service.add(usr);
    }
     @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(required = true) long id) {
        return  service.getUserById(id);
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable(required = true) long id) {
        service.delete(id);
    }

    @Autowired ToolService tool;

    @GetMapping("/tool")
    public Collection<Tool> getTools() {
        return tool.getTools();
    }
    
    @PostMapping("/tool")
    public void postTool(@RequestBody Tool tool) {
        this.tool.add(tool);
    }
     @GetMapping("/tool/{id}")
    //  @GetMapping
    public Tool getToolById(@PathVariable(required = true) long id) {
        return  tool.getToolById(id);
    }
    @DeleteMapping("/tool/{id}")
    public void deleteTool(@PathVariable(required = true) long id) {
        tool.delete(id);
    }
    @Autowired ScanService scan;

    @GetMapping("/scan")
    public Collection<Scan> getScan() {
        return scan.getScan();
    }
    
    @PostMapping("/scan")
    public void postScan(@RequestBody Scan t) {
        scan.add(t);
    }
     @GetMapping("/scan/{id}")
    //  @GetMapping
    public Scan getScanById(@PathVariable(required = true) long id) {
        return  scan.getScanById(id);
    }
    @DeleteMapping("/scan/{id}")
    public void deleteScan(@PathVariable(required = true) long id) {
        scan.delete(id);
    }

    @Autowired ProjectService project;

    @GetMapping("/project")
    public Collection<Project> getProject() {
        return project.getProject();
    }
    
    @PostMapping("/project")
    public void postProject(@RequestBody Project project) {
        this.project.add(project);
    }
     @GetMapping("/project/{id}")
    public Project getProjectById(@PathVariable(required = true) long id) {
        return  project.getProjectById(id);
    }
    @DeleteMapping("/project/{id}")
    public void deleteProject(@PathVariable(required = true) long id) {
        project.delete(id);
    }

    @Autowired TeamService team;

    @GetMapping("/team")
    public Collection<Team> getTeam() {
        return team.getTeam();
    }
    
    @PostMapping("/team")
    public void postTeam(@RequestBody Team t) {
        team.add(t);
    }
     @GetMapping("/team/{id}")
    //  @GetMapping
    public Team getTeamById(@PathVariable(required = true) long id) {
        return  team.getTeamById(id);
    }
    @DeleteMapping("/team/{id}")
    public void deleteTeam(@PathVariable(required = true) long id) {
        team.delete(id);
    }

}
