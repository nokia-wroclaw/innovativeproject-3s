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
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;

    private String email;

    private int active;

    private String roles;

    private String permissions;

    protected User() {}

    public User(String login, String password, String email, String roles, String permissions) {
        this.login = login;
        this.password = password;
        this.email=email;
        this.roles=roles;
        this.permissions=permissions;
        this.active=1;
    }
    public Long getId(){
        return id;
    }
    public String getLogin(){
        return login;
    }
    public String getPassword(){
        return password;
    }
    public String getRoles(){
        return roles;
    }
    public String getPermissions(){
        return permissions;
    }
    public String getEmail(){
        return email;
    }

    public int getActive(){
        return active;
    }

    public List<String> getRolelist(){
        if(this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
    
    public List<String> getPermissionsList(){
        if(this.permissions.length()>0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
