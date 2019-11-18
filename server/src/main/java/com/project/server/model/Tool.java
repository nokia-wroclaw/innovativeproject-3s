package com.project.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity

@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class Tool {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String toolname;
    private String info;
    
    protected Tool() {}

    public Tool(String toolname, String info) {
        this.toolname = toolname;
        this.info = info;
    }

    @Override
    public String toString() {
        return String.format(
                "Tool[id=%d, username='%s', password='%s']",
                id, toolname, info);
    }

    public Long getId() {
        return id;
    }

    public String gettoolname() {
        return toolname;
    }

    public String getInfo() {
        return info;
    }
}
