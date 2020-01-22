package com.project.server.controllers;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


import com.project.server.model.*;
import com.project.server.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.server.Application;
import com.project.server.job.ScanJob;
import com.project.server.model.ScheduleScanRequest;
import com.project.server.model.Scan;
import com.project.server.model.ScheduleScanResponse;
import org.quartz.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.project.server.model.ScanData;
import com.project.server.repository.ScanRepository;
import com.project.server.services.ScanService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class ScanJobSchedulerCronController {
    private static final Logger logger = LoggerFactory.getLogger(ScanJobSchedulerController.class);


    @Autowired
    ScanService scanService;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScanRepository scanRepo;

    @PostMapping("/scheduleScanCron")
    public ResponseEntity<ScheduleScanResponse> scheduleScan(@Valid @RequestBody ScanData scheduleScanRequest) {
        try {

    
            Scan newScan = scanService.createScanAndAdd(scheduleScanRequest.getCron(), "scheduled", scheduleScanRequest.getLogin(), scheduleScanRequest.getEmail(), scheduleScanRequest.getToolName(), scheduleScanRequest.getProjectName(), "no result yet");
   

            String cron = scheduleScanRequest.getCron();
            JobDetail jobDetail = buildJobDetail(newScan);
            CronTrigger trigger = newTrigger().forJob(jobDetail).withIdentity(jobDetail.getKey().getName(), "email-triggers").withSchedule(cronSchedule(cron)).build();
            scheduler.scheduleJob(jobDetail, trigger);

            ScheduleScanResponse scheduleScanResponse = new ScheduleScanResponse(true,
                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Scan Scheduled Successfully!");
            return ResponseEntity.ok(scheduleScanResponse);
        } catch (Exception ex) {
            logger.error("Error scheduling scan", ex);
      
            ScheduleScanResponse scheduleScanResponse = new ScheduleScanResponse(false,
                    "Error scheduling scan. Please try later!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scheduleScanResponse);
        }
    }

    private JobDetail buildJobDetail(Scan newScan) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("id", newScan.getId());      
        jobDataMap.put("email", newScan.getEmail());
        jobDataMap.put("tool", newScan.getToolName());

        return JobBuilder.newJob(ScanJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Scan Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }


}
