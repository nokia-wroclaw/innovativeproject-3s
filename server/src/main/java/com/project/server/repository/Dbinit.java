package com.project.server.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import  com.project.server.model.User;

import java.util.List;

@Service
public class Dbinit implements CommandLineRunner{
    private UserRepository UserRepository
    
    public Dbinit(UserRepository UserRepository){
        this.UserRepository=UserRepository;
    }
    @Override
    public void run(String... args){

        User dan= new User("dan","123","dan11@gmail.com","USER","");
        User jan= new User("jan","123","jan11@gmail.com","USER","test1");
        User pan= new User("pan","123","pan11@gmail.com","USER","test2");
        List<User> users=Arrays.asList(dan,jan,pan);
        this.UserRepository.saveAll(users);

    }
}