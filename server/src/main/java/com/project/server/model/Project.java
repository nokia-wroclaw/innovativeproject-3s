package com.project.server.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Collection<Team> team = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="project_tool",
            joinColumns=@JoinColumn(name="project_id"),
            inverseJoinColumns=@JoinColumn(name="tool_id"))
    @JsonIgnore            
    private Collection<Tool> tool = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="project_scan",
            joinColumns=@JoinColumn(name="project_id"),
            inverseJoinColumns=@JoinColumn(name="scan_id"))
    @JsonIgnore
    private Collection<Scan> scan = new ArrayList<>();

    public Project() {}

    public Project(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Collection<Team> getTeam() {
        return team;
    }
    public void setTeam(Collection<Team> team) {
        this.team = team;
    }
    public Collection<Tool> getTool() {
        return tool;
    }
    public void setTool(Collection<Tool> tool) {
        this.tool = tool;
    }

    public Collection<Scan> getScan() {
        return scan;
    }

    public void setScan(Collection<Scan> scan) {
        this.scan = scan;
    }
}
