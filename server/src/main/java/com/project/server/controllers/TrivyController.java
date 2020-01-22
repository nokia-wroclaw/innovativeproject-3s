package com.project.server.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.project.server.model.User;

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
import org.springframework.web.bind.annotation.RequestHeader;
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
    @PostMapping("/trivy")
    public void privRepoauthentication( @RequestBody ScanData scandata ) throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {   
        
        String authentication = "-e TRIVY_AUTH_URL=https://registry.hub.docker.com -e TRIVY_USERNAME="+ scandata.getUsername() +" -e TRIVY_PASSWORD=" + scandata.getPassword();
        String cmd4="docker run --rm " +  (scandata.getisPrivate().equals("true") ? authentication : "")  +  " aquasec/trivy -f json --light -q "+ scandata.getName();
        Process proc4 = Runtime.getRuntime().exec(cmd4);

        BufferedReader stdInput4 = new BufferedReader(new 
        InputStreamReader(proc4.getInputStream()));    
        String s4="";
        StringBuilder sb=new StringBuilder();
        while ((s4 = stdInput4.readLine()) != null) {
            System.out.println(s4);
            sb.append(s4);
        }
        sendMail(mailProperties.getUsername(), scandata.getEmail(),"scan", sb.toString());
        
    }


  private void sendMail(String fromEmail, String toEmail, String subject, String body) {
                   try {
                       logger.info("Sending Email to {}", toEmail);
                       MimeMessage message = mailSender.createMimeMessage();
                       logger.info("Sending Email to {}", toEmail);

                       MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
                       logger.info("Sending Email to {}", toEmail);

                       messageHelper.setSubject(subject);
                       messageHelper.setText(body, false);
                       messageHelper.setFrom(fromEmail);
                       messageHelper.setTo(toEmail);
            
                       mailSender.send(message);
                   } catch (MessagingException ex) {
                       logger.error("Failed to send email to {}", toEmail);
                   }
               }
 
}
