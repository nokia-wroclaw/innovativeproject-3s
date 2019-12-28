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
public class Tool {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 100)
    private String name;
    private String image;
    private String info;

    @ManyToMany(mappedBy = "tools", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Collection<Project> project = new ArrayList<>();

    public Collection<Project> getProject() {
        return project;
    }
    public void setProject(Collection<Project> project) {
        this.project = project;
    }

    protected Tool() {}

    public Tool(String name, String image, String info) {
        this.name = name;
        this.image = image;
        this.info = info;
    }

    @Override
    public String toString() {
        return String.format(
                "Tool[id=%d, name='%s', info='%s']",
                id, name, info);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
