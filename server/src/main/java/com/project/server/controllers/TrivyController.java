package com.project.server.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.project.server.model.User;
import com.project.server.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class TrivyController {

    @RequestMapping("/trivy")
    public String startTestcmd(@RequestBody String img){
        StringBuilder sb=new StringBuilder();

        try{    
            // Run a shell script
            String cmd ="docker run --rm  aquasec/trivy -f json --light "+img;
        Process proc = Runtime.getRuntime().exec(cmd);
        System.out.println("Success!");

        BufferedReader stdInput = new BufferedReader(new 
        InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new 
        InputStreamReader(proc.getErrorStream()));

        // Read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            sb.append(s+"\n");        
        }

        // Read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
             }
             System.out.println(sb.toString());
        return sb.toString();   
 }
}
