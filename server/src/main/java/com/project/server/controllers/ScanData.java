package com.project.server.controllers;

import com.project.server.model.Scan;

public class ScanData {
    private Scan scan;
    private String username;
    private String password;
    private String name; // this is login/reponame:version
    private String isPrivate;
    private String email;

    public Scan getScan() {
        return scan;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getisPrivate() {
        return isPrivate;
    }

    public void setisPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScan(Scan scan) {
        this.scan = scan;
    }

	public String getEmail() {
		return email;
	}
    
}
