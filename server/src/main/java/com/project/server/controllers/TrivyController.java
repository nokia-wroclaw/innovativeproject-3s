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
    @PostMapping("/repo")
    public void privRepoauthentication( @RequestHeader("login") String login, @RequestHeader("psw") String psw,@RequestHeader("repo") String repo, @RequestHeader("header") String version) throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        
        
        // String cmd4="docker run --rm  aquasec/trivy -f json --quiet --light danieldrapala/"+repo+":"+version;
        // Process proc4 = Runtime.getRuntime().exec(cmd4);
        System.out.println(login +" "+psw+" "+ repo+" "+version);
   
        Map<String, String> env = System.getenv();
        Field field = env.getClass().getDeclaredField("m");
        field.setAccessible(true);
        ((Map<String, String>) field.get(env)).put("TRIVY_USERNAME", login);
        ((Map<String, String>) field.get(env)).put("TRIVY_PASSWORD", psw);

        String cmd4="docker run --rm  -e TRIVY_AUTH_URL -e TRIVY_USERNAME -e TRIVY_PASSWORD aquasec/trivy -f json --light "+ login+"/"+repo+":"+version;
        Process proc4 = Runtime.getRuntime().exec(cmd4);
        System.out.println(System.getenv("TRIVY_AUTH_URL") +" "+System.getenv("TRIVY_USERNAME")+" "+ System.getenv("TRIVY_PASSWORD")+" ");

        BufferedReader stdInput4 = new BufferedReader(new 
        InputStreamReader(proc4.getInputStream()));    
        String s4="";
        StringBuilder sb=new StringBuilder();
        while ((s4 = stdInput4.readLine()) != null) {
            System.out.println(s4);
            sb.append(s4);
        }
        sendMail(mailProperties.getUsername(), "danieldr1212@gmail.com", "scan", sb.toString());
        
    }

    @RequestMapping("/trivy")
    public String startTestcmd(){
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
        System.out.println("Here is the standard output of the command: danieldrapala/3s:latest\n");
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
        System.out.println(body);
       sendMail(mailProperties.getUsername(), "danieldr1212@gmail.com", "scan", body);
       return body;
    } 
  private void sendMail(String fromEmail, String toEmail, String subject, String body) {
                   try {
                       logger.info("Sending Email to {}", toEmail);
                       MimeMessage message = mailSender.createMimeMessage();
                       logger.info("Sending Email to {}", toEmail);

                       MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
                       logger.info("Sending Email to {}", toEmail);

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
