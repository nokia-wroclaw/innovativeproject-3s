package com.project.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.server.model.*;
import java.util.List;
import java.util.Map;

@RestController
@RequetMapping("/loggedin")
public class UserController {

    @Autowired
    UserRepository  UserRespository;

    @GetMapping("")
    public User show(@PathVariable String id){
        int userId = Integer.parseInt(id);
        return UserRespository.findById(userId);
    }
}