package com.project.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "team")
    private Collection<User> user = new ArrayList<>();

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
