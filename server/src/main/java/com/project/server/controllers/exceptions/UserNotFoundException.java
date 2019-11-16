package com.project.server.controllers.exceptions;

import com.project.server.model.User;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(User user) {
        super("Could not find user {" + user.getId() + ", " + user.getUsername() + ", " + user.getPassword() + "}");
    }

    public UserNotFoundException(Long id) {
        super("Could not find user {id: " + id + "}");
    }
}
