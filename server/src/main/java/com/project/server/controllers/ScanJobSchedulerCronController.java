package com.project.server.controllers;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import com.project.server.Application;
import com.project.server.job.ScanJob;
import com.project.server.model.ScheduleScanRequest;
import com.project.server.model.Scan;
import com.project.server.model.ScheduleScanResponse;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.server.repository.*;
import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@RestController
public class ScanJobSchedulerCronController {
    private static final Logger logger = LoggerFactory.getLogger(ScanJobSchedulerController.class);

    @Autowired
    private Scheduler scheduler;
    public ScanRepository scanRepo;

    @PostMapping("/scheduleScanCron")
    public ResponseEntity<ScheduleScanResponse> scheduleScan(@Valid @RequestBody Scan scheduleScanRequest) {
        try {

            #Scan newDatabaseScan = scanRepo.save(scheduleScanRequest);

            String cron = scheduleScanRequest.getStringDate();
            JobDetail jobDetail = buildJobDetail(scheduleScanRequest);
            CronTrigger trigger = newTrigger().forJob(jobDetail).withIdentity(jobDetail.getKey().getName(), "email-triggers").withSchedule(cronSchedule(cron)).build();
            scheduler.scheduleJob(jobDetail, trigger);

            ScheduleScanResponse scheduleScanResponse = new ScheduleScanResponse(true,
                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Scan Scheduled Successfully!");
            return ResponseEntity.ok(scheduleScanResponse);
        } catch (Exception ex) {
            logger.error("Error scheduling scan", ex);
            ex.printStackTrace();
            System.out.println(ex);       
            ScheduleScanResponse scheduleScanResponse = new ScheduleScanResponse(false,
                    "Error scheduling scan. Please try later!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scheduleScanResponse);
        }
    }

    private JobDetail buildJobDetail(Scan scheduleScanRequest) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("email", scheduleScanRequest.getEmail());
        jobDataMap.put("tool", scheduleScanRequest.getToolName());

        return JobBuilder.newJob(ScanJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Scan Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }


}
