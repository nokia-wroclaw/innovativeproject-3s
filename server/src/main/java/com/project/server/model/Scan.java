package com.project.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;


import static java.awt.SystemColor.info;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class Scan {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String result;
    private String tool;
    private String email;
    private LocalDateTime dateTime;
    private ZoneId timeZone;

    @ManyToMany(mappedBy = "scan", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Project> project = new ArrayList<>();
    public Collection<Project> getProject() {
        return project;
    }

    public int getId() {
        return id;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(ZoneId timeZone) {
        this.timeZone = timeZone;
    }
}
