package com.project.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Permission {
    public enum PermissionType {
        USER,
        ADMIN;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private PermissionType type;

    @ManyToMany(mappedBy = "permission", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<User> user = new ArrayList<>();

    public Permission() {}

    public Permission(String type) {
        this.type = PermissionType.valueOf(type);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type.toString();
    }
    public void setType(String type) {
        this.type = PermissionType.valueOf(type);
    }
    public Collection<User> getUser() {
        return user;
    }
    public void setUser(Collection<User> user) {
        this.user = user;
    }
}
