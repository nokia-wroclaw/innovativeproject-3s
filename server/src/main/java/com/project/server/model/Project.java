package com.project.server.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@JsonIgnoreProperties(allowGetters = true)
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "projects", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Collection<User> users = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="project_tool",
            joinColumns=@JoinColumn(name="project_id"),
            inverseJoinColumns=@JoinColumn(name="tool_id"))
    @JsonIgnore
    private Collection<Tool> tools = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Collection<Scan> scans = new ArrayList<>();

    public Project() {}

    public Project(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Collection<User> getUsers() {
        return users;
    }
    public void setUsers(Collection<User> user) {
        this.users = users;
    }
    public Collection<Tool> getTools() {
        return tools;
    }
    public void setTool(Collection<Tool> tools) {
        this.tools = tools;
    }

    public Collection<Scan> getScans() {
        return scans;
    }

    public void setScan(Collection<Scan> scans) {
        this.scans = scans;
    }
}