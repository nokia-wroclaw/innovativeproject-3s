package com.project.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class Tool {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String info;

    @ManyToMany(mappedBy = "tool", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Collection<Project> project = new ArrayList<>();
    public Collection<Project> getProject() {
        return project;
    }


    protected Tool() {}

    public Tool(String name, String info) {
        this.name = name;
        this.info = info;
    }

    @Override
    public String toString() {
        return String.format(
                "Tool[id=%d, name='%s', info='%s']",
                id, name, info);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
