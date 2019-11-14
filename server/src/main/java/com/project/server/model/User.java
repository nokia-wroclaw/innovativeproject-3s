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
    private String login;
    private String password;
    private String email;
    protected User() {}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public User(String login, String password,String email) {
        this.login = login;
        this.password = password;
        this.email=email;
    }


    @Override
    public String toString() {
        return String.format(
                "User[id=%d, login='%s', password='%s',   email='%s';                ]",
                id, login, password,email);
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id= id;
    }

    public void setLogin(String login) {
        this.login= login;
    }

    public void setPassword(String Password) {
        this.password= Password;
    }
    public void setEmail(String email) {
        this.email= email;
    }


}
