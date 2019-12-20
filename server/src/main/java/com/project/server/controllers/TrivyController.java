package com.project.server.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

//import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.project.server.model.User;
import com.project.server.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
    private static final Logger logger = LoggerFactory.getLogger(TrivyController.class);
    
       @Autowired
       private JavaMailSender mailSender;
    
       @Autowired
       private MailProperties mailProperties;
    @RequestMapping("/trivy")
    public void startTestcmd(){
        StringBuilder sb=new StringBuilder();

        try {
            // Run a shell script
        String cmd ="docker run --rm  aquasec/trivy -f json --quiet --light python:3.4-alpine ";
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

        String body = sb.toString();

       sendMail(mailProperties.getUsername(), "danieldr1212@gmail.com", "scan", body); sb.toString();  
    } 
  private void sendMail(String fromEmail, String toEmail, String subject, String body) {
                   try {
                       logger.info("Sending Email to {}", toEmail);
                       MimeMessage message = mailSender.createMimeMessage();
            
                       MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
                       messageHelper.setSubject(subject);
                       messageHelper.setText(body, true);
                       messageHelper.setFrom(fromEmail);
                       messageHelper.setTo(toEmail);
            
                       mailSender.send(message);
                   } catch (MessagingException ex) {
                       logger.error("Failed to send email to {}", toEmail);
                   }
               }
 
}
