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
    ScanService scanService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;
    
    @Autowired
    public ScanRepository scanRepo;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
   
        String s4="";
        StringBuilder sb=new StringBuilder();
        try{
        String cmd4="docker run --rm  aquasec/trivy -f json --light -q "+ jobDataMap.getString("tool");
        Process proc4 = Runtime.getRuntime().exec(cmd4);
        BufferedReader stdInput4 = new BufferedReader(new 
        InputStreamReader(proc4.getInputStream()));  

        while ((s4 = stdInput4.readLine()) != null) {
            System.out.println(s4);
            sb.append(s4);
        }}
         catch (IOException e) {
        e.printStackTrace();
        }




        long scanId = jobDataMap.getLong("id");


        Optional<Scan> optionalScanToUpdate = scanRepo.findById((long) scanId);
        Scan scanToUpdate = optionalScanToUpdate.get();
        scanToUpdate.setContent(sb.toString());
        scanRepo.save(scanToUpdate);

        sendMail(mailProperties.getUsername(), jobDataMap.getString("email"), "Scan", sb.toString());
    }       BufferedReader stdInput = new BufferedReader(new
   

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
