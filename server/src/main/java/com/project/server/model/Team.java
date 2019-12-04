package com.project.server.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "team")
    @JsonIgnore
    private Collection<User> user = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="team_project",
            joinColumns=@JoinColumn(name="team_id"),
            inverseJoinColumns=@JoinColumn(name="project_id"))
    @JsonIgnore
    private Collection<Project> project=new ArrayList<>();

    public Collection<Project> getProject() {
        return project;
    }
    public void setProjects(Collection<Project> project) {
        this.project = project;
    }

    public Team() {}

    public Team(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Collection<User> getUser() {
        return user;
    }
    public void setUser(Collection<User> user) {
        this.user = user;
    }
}
