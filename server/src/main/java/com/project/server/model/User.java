package com.project.server.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity

@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="user_permission",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="permission_type"))
    @JsonIgnore
    private Collection<Permission> permission=new ArrayList<>();

    public Collection<Permission> getPermissions() {
        return permission;
    }
    public void setPermissions(Collection<Permission> permissions) {
        this.permission = permissions;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="user_team",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="team_id") )
    private Collection<Team> team=new ArrayList<>();

    public Collection<Team> getTeams() {
        return team;
    }
    public void setTeams(Collection<Team> teams) {
        this.team = teams;
    }

    protected User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, username='%s', password='%s']",
                id, username, password);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
