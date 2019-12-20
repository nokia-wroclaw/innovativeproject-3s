package com.project.server.job;

import org.springframework.boot.CommandLineRunner;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@Component
public class ScanJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(ScanJob.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());

        StringBuilder scanResult = new StringBuilder();

        try{

            // Run a shell script

            Process proc = Runtime.getRuntime().exec("docker run --rm  aquasec/trivy -f json --light python:3.4-alpine");
            System.out.println("Success!");

            BufferedReader stdInput = new BufferedReader(new
            InputStreamReader(proc.getInputStream()));


            // Read the output from the command
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                scanResult.append(s);
                scanResult.append("\n");
                System.out.println(s);
        }


        } catch (IOException e) {
            e.printStackTrace();
        }

        String scanEmail = scanResult.toString();
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String subject = jobDataMap.getString("subject");
        String body = scanEmail;
        String recipientEmail = jobDataMap.getString("email");

        sendMail(mailProperties.getUsername(), recipientEmail, subject, body);
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
