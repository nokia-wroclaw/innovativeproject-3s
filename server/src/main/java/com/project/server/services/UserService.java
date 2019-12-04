package com.project.server.services;

import java.util.List;
import java.util.Optional;

import com.project.server.controllers.exceptions.UserNotFoundException;
import com.project.server.model.User;
import com.project.server.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService  {
    @Autowired UserRepository repository;

	public List<User> getUsers() {
        return (List<User>) repository.findAll();
	}

	public void add(User usr) {
        repository.save(usr);
	}

	public User getUserById(long id) {
        Optional<User> optionalusr = repository.findById(id);
        return (User) optionalusr.orElseThrow(() -> new UserNotFoundException(id));	}

	public void delete(long id) {
        repository.deleteById(id);
	}

 
    
}
