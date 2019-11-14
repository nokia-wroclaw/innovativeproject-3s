package com.project.server.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    public String testMsg() {
        return "test works";
    }
}
