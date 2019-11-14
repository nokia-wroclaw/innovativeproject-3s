package com.project.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String status;
    private String login;
    private String password;

    protected User() {}

    public User(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public User(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, login='%s', password='%s']",
                id, login, password);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
