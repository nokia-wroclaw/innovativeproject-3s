package com.project.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.server.model.*;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserRepository  UserRespository;

    @GetMapping("/user")
    public List<User> index(){
        return UserRespository.findAll();
    }

    @GetMapping("/user/{id}")
    public User show(@PathVariable String id){
        int userId = Integer.parseInt(id);
        return UserRespository.findById(userId);
    }

    @PostMapping("/user/search")
    public List<User> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return UserRespository.findByLogin(searchTerm);
    }

    @PostMapping("/user")
    public User create(@RequestBody Map<String, String> body){
        String login = body.get("login");
        String password = body.get("password");
        String email =body.get("email");
        return UserRespository.save(new User(login, password,email));
    }

    @PutMapping("/user/{id}")
    public User update(@PathVariable String id, @RequestBody Map<String, String> body){
        int blogId = Integer.parseInt(id);
        // getting blog
        User user = UserRespository.findById(blogId);
        user.setLogin(body.get("login"));
        user.setPassword(body.get("content"));
        return UserRespository.save(user);
    }

    @DeleteMapping("blog/{id}")
    public boolean delete(@PathVariable String id){
            User user= UserRespository.findById(Integer.parseInt(id));
        UserRespository.delete(user);
        return true;
    }


}